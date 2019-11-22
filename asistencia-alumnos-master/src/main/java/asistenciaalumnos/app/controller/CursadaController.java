package asistenciaalumnos.app.controller;

import asistenciaalumnos.app.model.Cursada;
import asistenciaalumnos.app.model.DTO.CursadaDto;
import asistenciaalumnos.app.service.CursadaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class CursadaController {

    @Autowired
    CursadaService cursadaService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static final Long ADMINISTRATIVO = 1L;
    private static final Long DOCENTE = 2L;

    private static  final Logger LOGGER = LogManager.getLogger(CursadaController.class);

    @GetMapping(path = "/cursadas")
    public ResponseEntity<?> cursadas() throws Exception {
        List<CursadaDto> cursadas = new ArrayList<CursadaDto>();
        try {
            cursadas = cursadaService.findAll();
        } catch (Exception ex) {
            LOGGER.error(HttpStatus.INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
            return new ResponseEntity<List<CursadaDto>>(cursadas, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<CursadaDto>>(cursadas, HttpStatus.OK);
    }

    @GetMapping(path = "/cursadas/{id}")
    public ResponseEntity<?> getCursada(@PathVariable("id") Long id) throws Exception {
        Cursada cursada = cursadaService.findById(id);
        if (cursada == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        CursadaDto cursadaDto = new CursadaDto(cursada);
        return new ResponseEntity<CursadaDto>(cursadaDto, HttpStatus.OK);
    }

    @PutMapping(path = "/updateCursada")
    public ResponseEntity<?> modificacionCursada(@RequestBody Cursada cursada) throws Exception {
        Cursada cursadaToPersist = cursadaService.findById(cursada.getId());
        if (Objects.isNull(cursadaToPersist)) {
            LOGGER.error(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        Cursada cursadaPersisted = cursadaService.updateCursada(cursada,"logged usser");
        CursadaDto cursadaDto = new CursadaDto(cursadaPersisted);
        return new ResponseEntity<CursadaDto>(cursadaDto, HttpStatus.OK);
    }

    @PostMapping(path = "/newCursada")
    public ResponseEntity<?> altaCursada(@RequestBody Cursada cursada) throws Exception {
        Date date =  new Date();
        Cursada cursadaPersisted = cursadaService.newCursada(cursada, "logged usser", date);
        CursadaDto cursadaDto = new CursadaDto(cursadaPersisted);
        return new ResponseEntity<CursadaDto>(cursadaDto, HttpStatus.OK);
    }

}
