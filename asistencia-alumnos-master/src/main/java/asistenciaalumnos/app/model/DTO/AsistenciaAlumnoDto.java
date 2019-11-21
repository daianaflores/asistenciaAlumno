package asistenciaalumnos.app.model.DTO;

import asistenciaalumnos.app.model.AsistenciaAlumno;
import asistenciaalumnos.app.model.AsistenciaAlumnoId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class AsistenciaAlumnoDto {
    @JsonIgnore
    private final AsistenciaAlumno asistenciaAlumno;

    public AsistenciaAlumnoDto(AsistenciaAlumno asistenciaAlumno){this.asistenciaAlumno = asistenciaAlumno; }

    public AsistenciaAlumnoId getId(){return this.asistenciaAlumno.getId();}

    public String getAlumno(){
        return this.asistenciaAlumno.getAlumno().getId() +" "+
                this.asistenciaAlumno.getAlumno().getNombre() +" "+
                this.asistenciaAlumno.getAlumno().getApellido();
    }

    public String getAsistencia(){
       return "Asistencia id : " + this.asistenciaAlumno.getAsistencia().getId().toString();
    }

    public Boolean getPresent(){return this.asistenciaAlumno.getPresent();}

}
