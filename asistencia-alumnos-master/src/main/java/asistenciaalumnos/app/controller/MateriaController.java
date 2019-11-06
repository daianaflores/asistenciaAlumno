package asistenciaalumnos.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import asistenciaalumnos.app.model.DTO.AlumnoDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import asistenciaalumnos.app.model.Alumno;
import asistenciaalumnos.app.service.AlumnoService;

@RestController
@CrossOrigin(origins = "*", 
             methods= {RequestMethod.GET, 
                       RequestMethod.POST,
                       RequestMethod.PUT,
                       RequestMethod.DELETE})
public class AlumnoController 
{
    @Autowired
    AlumnoService materiaService;

    //ejemplo instanciar logger
    private static  final Logger LOGGER = LogManager.getLogger(MateriaController.class);


    @GetMapping(path = "/")
    public String hello() throws Exception 
    {
        //ejemplos de loggeo
        LOGGER.debug("Debugging log");
        LOGGER.info("Info log");
        LOGGER.warn("Hey, This is a warning!");
        LOGGER.error("Oops! We have an Error. OK");
        LOGGER.fatal("Damn! Fatal error. Please fix me.");
        String message = alumnoService.getHello();
        return message;

    }

    @GetMapping(path = "/materia")
    public ResponseEntity<?> materias() throws Exception 
    {

        List<MateriaDto> materias = new ArrayList<MateriaDto>();
        try {
            materias = materiaService.getMaterias();
        } catch (Exception ex) {
            //ejemplo de implementación
            LOGGER.error(HttpStatus.INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
            return new ResponseEntity<List<AlumnoDto>>(materias, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<MateriasDto>>(materias, HttpStatus.OK);
    }

    @PostMapping(path = "/materia")
    public ResponseEntity<?> altaMateria(@RequestBody Materia materia) throws Exception
    {
        Materia materiaResponse = materiaService.altaMateria(materia);
        MateriaDto materiaDto = new MateriaDto(materia);

        return new ResponseEntity<MateriaDto>(materiaDto, HttpStatus.OK);
    }

    @PutMapping(path = "/materia")
    public ResponseEntity<?> modificacionMateria(@RequestBody Materia materia) throws Exception
    {
        Materia materiaResponse = materiaService.modificacionMateria(materia);
        MateriaDto materiaDto = new MateriaDto(materiaResponse);
        if (materiaResponse == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AlumnoDto>(materiaDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/materia/{id}")
    public ResponseEntity<?> bajaMateria(@PathVariable("id") Long id) throws Exception
    {
        materiaService.bajaMateria(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}