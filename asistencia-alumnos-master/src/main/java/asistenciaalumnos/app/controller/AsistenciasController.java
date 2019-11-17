package asistenciaalumnos.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import asistenciaalumnos.app.model.Alumno;
import asistenciaalumnos.app.model.Cursada;
import asistenciaalumnos.app.model.DTO.AsistenciaDto;
import asistenciaalumnos.app.model.Docente;
import asistenciaalumnos.app.service.impl.AsistenciaServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import asistenciaalumnos.app.model.Asistencia;
import asistenciaalumnos.app.service.AsistenciaService;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", 
             methods= {RequestMethod.GET, 
                       RequestMethod.POST,
                       RequestMethod.PUT,
                       RequestMethod.DELETE})

public class AsistenciasController {

    private static  final Logger LOGGER = LogManager.getLogger(AsistenciasController.class);

    @Autowired
    AsistenciaService asistenciaService;

    //no deberia hacer dos mapeos al service pero al estar separado en service y serviceImpl es lo más rápido.
    @Autowired
    AsistenciaServiceImpl asistenciaServiceImpl;

    private static final Long ADMINISTRATIVO = 1L;
    private static final Long DOCENTE = 2L;

    @GetMapping(path = "/asistenciaHello")
    public String hello() throws Exception {
        String message = asistenciaService.getHello();
        return message;
    }

    @GetMapping(path = "/asistencias")
    public ResponseEntity<?> asistencias() throws Exception{
        List<AsistenciaDto> asistencias = asistenciaService.getAsistencias();
        return new ResponseEntity<List<AsistenciaDto>>(asistencias, HttpStatus.OK);
    }

    @GetMapping(path = "/findAsistencias")
    public ResponseEntity<?> findAsistencias(@RequestParam("fecha")  Date fecha,
                                             @RequestParam("cursada")Cursada cursada) throws Exception{
        List<AsistenciaDto> asistencias = asistenciaService.findAsistenciasByFechaAndCursada(cursada,fecha);
        return new ResponseEntity<List<AsistenciaDto>>(asistencias, HttpStatus.OK);
    }

    //grabar la asistencia
    @PostMapping(path = "/newAssistance")
    public ResponseEntity<?> takeAssistance(@RequestParam("cursada")Cursada cursada,@RequestParam("alumnoList")List<Alumno> alumnoList,
                           @RequestParam("fecha")  Date fecha )  throws Exception {

            Asistencia asistenciaResponse = asistenciaService.altaAsistencia(cursada, alumnoList, fecha);
            return new ResponseEntity<Asistencia>(asistenciaResponse, HttpStatus.OK);

    }

    @PutMapping(path = "/updateAssistance")
    public ResponseEntity<?> modificacionAsistencia(@RequestBody Asistencia asistencia,
                                                    @RequestParam("fecha")  Date fecha) throws Exception{

            Asistencia asistenciaResponse = asistenciaService.modificacionAsistencia(asistencia, fecha);
            if (asistenciaResponse == null) {
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Asistencia>(asistenciaResponse, HttpStatus.OK);
    }

    @DeleteMapping(path = "/asistencia/{id}")
    public ResponseEntity<?> bajaAsistencia(@PathVariable("id") Long id) throws Exception {
        Asistencia aObject = asistenciaServiceImpl.findById(id);
        asistenciaService.bajaAsistencia(aObject);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
