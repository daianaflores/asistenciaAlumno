package asistenciaalumnos.app.model.DTO;

import asistenciaalumnos.app.model.Contacto;
import asistenciaalumnos.app.model.Docente;
import asistenciaalumnos.app.model.Estado;
import asistenciaalumnos.app.model.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class DocenteDto {

    @JsonIgnore
    private final Docente docente;

    public DocenteDto(Docente docente){this.docente = docente;}

    public Long getId(){return this.docente.getId();}

    public TipoUsuario getTipoUsuario(){return this.docente.getTipoUsuario();}

    public String getNombre(){
        return this.docente.getNombre();
    }

    public String getApelido(){
        return this.docente.getApellido();
    }

    public String getFulnnName(){
        return this.docente.getApellido()+" "+this.docente.getNombre();
    }

    public Long getDNI(){
        return this.docente.getDni();
    }

    public Date getFechaNacimiento(){
        return  this.docente.getFechaNacimiento();
    }

    public Long getLegajo(){
        return this.docente.getLegajo();
    }

    public Estado getEstado(){return this.docente.getEstado();}

    public Contacto getContacto(){return this.docente.getContacto();}


}
