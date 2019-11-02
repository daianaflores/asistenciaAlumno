package asistenciaalumnos.app.service;

import asistenciaalumnos.app.model.DTO.UsuarioDto;
import asistenciaalumnos.app.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface  UsuarioService {

    String getHello() throws Exception;

    List<UsuarioDto> getUsuarios() throws Exception;

    int altaUsuario(Usuario usuario) throws Exception;

    int modificacionUsuario(Usuario Usuario) throws Exception ;

    int bajaUsuario(Long id) throws Exception;


}
