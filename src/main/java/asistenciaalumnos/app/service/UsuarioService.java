package asistenciaalumnos.app.service;

import asistenciaalumnos.app.model.DTO.UsuarioDto;
import asistenciaalumnos.app.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface  UsuarioService {

    String getHello() throws Exception;

    List<UsuarioDto> getUsuarios() throws Exception;

    Usuario altaUsuario(Usuario usuario) throws Exception;

    Usuario modificacionUsuario(Usuario Usuario) throws Exception ;

    void bajaUsuario(Usuario Usuario) throws Exception;


}
