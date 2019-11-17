package asistenciaalumnos.app.controller;

import asistenciaalumnos.app.configs.CurrentUser;
import asistenciaalumnos.app.configs.UserDetails;
import asistenciaalumnos.app.model.DTO.MateriaDto;
import asistenciaalumnos.app.model.Materia;
import asistenciaalumnos.app.service.MateriaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*",
        methods= {RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE})
public class MateriaController {

    @Autowired
    MateriaService materiaService;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    private static final Long ADMINISTRATIVO = 1L;
    private static final Long DOCENTE = 2L;

    private static  final Logger LOGGER = LogManager.getLogger(AlumnoController.class);

    @GetMapping(path = "/materias")
    public ResponseEntity<?> materias() throws Exception{
        List<MateriaDto> materias = new ArrayList<MateriaDto>();
        try {
            materias = materiaService.findAll();
        } catch (Exception ex) {
            LOGGER.error(HttpStatus.INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
            return new ResponseEntity<List<MateriaDto>>(materias, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<MateriaDto>>(materias, HttpStatus.OK);
    }

    @PostMapping(path = "/newMateria")
    public ResponseEntity<?> altaMateriaa(@RequestBody Materia materia,@RequestParam("fecha")  String fecha) throws Exception {
            Date date =  sdf.parse(fecha);
        try {
                Materia materiaResponse = materiaService.bindProperties(materia, date);
                return new ResponseEntity<Materia>(materiaResponse, HttpStatus.OK);
            }catch(Exception e){
                e.printStackTrace();
            }
        return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping(path = "/updateMateria")
    public ResponseEntity<?> modificacionMateria(@RequestBody Materia materia) throws Exception{
        Materia materiaResponse = materiaService.findById(materia.getId());
        if(Objects.isNull(materiaResponse)){
            LOGGER.error(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        MateriaDto materiaDto = new MateriaDto(materiaResponse);
        materiaService.updateMateria(materia);
        return new ResponseEntity<MateriaDto>(materiaDto,HttpStatus.OK);
    }


    @DeleteMapping(path = "/materia/{id}")
    public ResponseEntity<?> bajaMateria(@PathVariable("id") Long id) throws Exception {
            Materia mObject = materiaService.findById(id);
            materiaService.bajaMateria(mObject);
            return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
