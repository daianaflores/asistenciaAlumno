package asistenciaalumnos.app.model.DTO;

import asistenciaalumnos.app.model.Asistencia;
import asistenciaalumnos.app.model.AsistenciaAlumno;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AsistenciaDto {

    //ejemplo instanciar logger
    private static  final Logger LOGGER = LogManager.getLogger(AsistenciaDto.class);

    @JsonIgnore
    private final Asistencia asistencia;

    public AsistenciaDto(Asistencia asistencia){this.asistencia = asistencia;}

    public Long getId(){return  this.asistencia.getId();}

    public CursadaDto getCursada(){return  new CursadaDto(this.asistencia.getCursada());}

    public Date getFecha(){return this.asistencia.getFecha();}

    public List<AsistenciaAlumnoDto> getAsitenciaAlumno(){
        Set<AsistenciaAlumno> asistenciaAlumnoSet = this.asistencia.getAsistenciaAlumnos();
       return asistenciaAlumnoSet.stream().map(it -> {return new AsistenciaAlumnoDto(it); }).collect(Collectors.toList());
    }

}
