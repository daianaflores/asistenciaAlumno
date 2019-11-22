package asistenciaalumnos.app.controller;

import asistenciaalumnos.app.model.Alumno;
import asistenciaalumnos.app.model.Asistencia;
import asistenciaalumnos.app.model.Cursada;
import asistenciaalumnos.app.model.DTO.AsistenciaDto;
import asistenciaalumnos.app.service.AsistenciaService;
import asistenciaalumnos.app.service.impl.AsistenciaServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", 
             methods= {RequestMethod.GET, 
                       RequestMethod.POST,
                       RequestMethod.PUT,
                       RequestMethod.DELETE})

public class AsistenciasController {

    private static  final Logger LOGGER = LogManager.getLogger(AsistenciasController.class);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
    public ResponseEntity<?> findAsistencias(@RequestParam("fecha")  String fecha,
                                             @RequestParam("cursada")Cursada cursada) throws Exception{
        Date date =  sdf.parse(fecha);
        List<AsistenciaDto> asistencias = asistenciaService.findAsistenciasByFechaAndCursada(cursada,date);
        return new ResponseEntity<List<AsistenciaDto>>(asistencias, HttpStatus.OK);
    }

    //grabar la asistencia
    @PostMapping(path = "/newAsistencia")
    public ResponseEntity<?> takeAssistance(@RequestBody Asistencia asistencia)  throws Exception {
        Date date =  new Date();
        Asistencia asistenciaResponse = asistenciaService.altaAsistencia(asistencia, date);
        AsistenciaDto asistenciaDto = new AsistenciaDto(asistenciaResponse);
        return new ResponseEntity<AsistenciaDto>(asistenciaDto, HttpStatus.OK);
    }

    @PutMapping(path = "/updateAsistencia")
    public ResponseEntity<?> modificacionAsistencia(@RequestBody Asistencia asistencia) throws Exception {
        Date date =  new Date();
        Asistencia asistenciaResponse = asistenciaService.modificacionAsistencia(asistencia, date);
        if (asistenciaResponse == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        AsistenciaDto asistenciaDto = new AsistenciaDto(asistenciaResponse);
        return new ResponseEntity<AsistenciaDto>(asistenciaDto, HttpStatus.OK);
    }

    @GetMapping(path = "/asistencia/{id}")
    public ResponseEntity<?> getAsistencia(@PathVariable("id") Long id) throws Exception {
        Asistencia aObject = asistenciaServiceImpl.findById(id);
        if (aObject == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        AsistenciaDto asistenciaDto = new AsistenciaDto(aObject);
        return new ResponseEntity<AsistenciaDto>(asistenciaDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/asistencia/{id}")
    public ResponseEntity<?> bajaAsistencia(@PathVariable("id") Long id) throws Exception {
        Asistencia aObject = asistenciaServiceImpl.findById(id);
        if (aObject == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        Asistencia aPersisted = asistenciaService.bajaAsistencia(aObject);
        AsistenciaDto asistenciaDto = new AsistenciaDto(aPersisted);
        return new ResponseEntity<AsistenciaDto>(asistenciaDto, HttpStatus.OK);
    }
}
