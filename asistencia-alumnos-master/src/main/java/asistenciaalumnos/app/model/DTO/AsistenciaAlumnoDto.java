package asistenciaalumnos.app.model.DTO;

import asistenciaalumnos.app.model.AsistenciaAlumno;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AsistenciaAlumnoDto {
    @JsonIgnore
    private final AsistenciaAlumno asistenciaAlumno;

    public AsistenciaAlumnoDto(AsistenciaAlumno asistenciaAlumno){this.asistenciaAlumno = asistenciaAlumno; }

    //public Long getId(){return this.asistenciaAlumno.getId();}

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
