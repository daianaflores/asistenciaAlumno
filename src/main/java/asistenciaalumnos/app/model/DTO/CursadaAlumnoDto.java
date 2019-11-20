/*package asistenciaalumnos.app.model.DTO;

import asistenciaalumnos.app.model.CursadaAlumno;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CursadaAlumnoDto {
    @JsonIgnore
    private final CursadaAlumno cursadaAlumno;

    public CursadaAlumnoDto(CursadaAlumno cursadaAlumno){this.cursadaAlumno = cursadaAlumno; }

    public Long getId(){return this.cursadaAlumno.getId();}

    public AlumnoDto getAlumno(){ return new AlumnoDto(this.cursadaAlumno.getAlumno());}

    public CursadaDto getCursada(){return new CursadaDto(this.cursadaAlumno.getCursada());}
}
*/