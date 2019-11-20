package asistenciaalumnos.app.model.DTO;

import asistenciaalumnos.app.model.Administrativo;
import asistenciaalumnos.app.model.Contacto;
import asistenciaalumnos.app.model.Estado;
import asistenciaalumnos.app.model.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class AdministrativoDTO {
    @JsonIgnore
    private final Administrativo administrativo;

    public AdministrativoDTO(Administrativo administrativo){ this.administrativo = administrativo; }

    public Long getId(){return this.administrativo.getId(); }

    public TipoUsuario getTipoUsuario(){
        return this.administrativo.getTipoUsuario();
    }

    public String getNombre(){
        return this.administrativo.getNombre();
    }

    public String getApelido(){
        return this.administrativo.getApellido();
    }

    public String getFullName(){
        return this.administrativo.getApellido()+" "+this.administrativo.getNombre();
    }

    public Long getDNI(){
        return this.administrativo.getDni();
    }

    public Date getFechaNacimiento(){
        return  this.administrativo.getFechaDeNacimiento();
    }

    public Long getLegajo(){
        return this.administrativo.getLegajo();
    }

    public Estado getEstado(){return this.administrativo.getEstado();}

    public Contacto getContacto(){return this.administrativo.getContacto();}
}
