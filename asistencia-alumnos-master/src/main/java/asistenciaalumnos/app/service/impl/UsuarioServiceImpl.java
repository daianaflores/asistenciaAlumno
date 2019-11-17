package asistenciaalumnos.app.service.impl;

import asistenciaalumnos.app.Dao.UsuarioDAO;
import asistenciaalumnos.app.model.Administrativo;
import asistenciaalumnos.app.model.DTO.AdministrativoDTO;
import asistenciaalumnos.app.model.DTO.DocenteDto;
import asistenciaalumnos.app.model.DTO.UsuarioDto;
import asistenciaalumnos.app.model.Docente;
import asistenciaalumnos.app.model.Usuario;
import asistenciaalumnos.app.repository.UsuarioRepository;
import asistenciaalumnos.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Long DADO_DE_BAJA = 1L;

    @Autowired
    UsuarioDAO usuarioDAO;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public String getHello() throws Exception
    {
        String message = "Hello World from API REST Spring Boot!!";
        return message;
    }

    @Override
    public List<UsuarioDto> getUsuarios() throws Exception {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDto> UsuarioDtoList = usuarios.stream().map(it -> new UsuarioDto(it)).collect(Collectors.toList());
        return UsuarioDtoList;
    }

    public List<DocenteDto> getDocentes() throws Exception {
        List<Docente> docentes = usuarioDAO.findDocentes();
        List<DocenteDto> docenteDtoList = docentes.stream().map(it -> new DocenteDto(it)).collect(Collectors.toList());
        return docenteDtoList;
    }

    public List<AdministrativoDTO> getAdministrativos() throws Exception {
        List<Administrativo> administrativos = usuarioDAO.findAdministrativos();
        List<AdministrativoDTO> admDtoList = administrativos.stream().map(it -> new AdministrativoDTO(it)).collect(Collectors.toList());
        return admDtoList;
    }


    public Docente findDocenteById(Long id) throws Exception{
        Docente docente = null;
        return docente = usuarioDAO.findOneDocente(id);
    }

    public Administrativo findAdministrativoById(Long id) throws Exception{
        Administrativo administrativo = null;
        return administrativo = usuarioDAO.findOneAdministrativo(id);
    }

    @Override
    public Usuario altaUsuario(Usuario usuario) throws Exception{
        Usuario usuarioResponse = usuarioRepository.saveAndFlush(usuario);
        return usuarioResponse;
    }

    @Override
    public Usuario modificacionUsuario(Usuario usuario) throws Exception{
        boolean alumnoExist = usuarioRepository.existsById(usuario.getId());
        if (!alumnoExist){
            return null;
        }
        Usuario alumnoResponse = usuarioRepository.saveAndFlush(usuario);
        return alumnoResponse;
    }

    @Override
    public void bajaUsuario(Usuario uObject) throws Exception{
        uObject.getEstado().setId(DADO_DE_BAJA);
    }

    public Usuario findById(Long id){
        Optional<Usuario> uObject = usuarioRepository.findById(id);
        if(uObject.isPresent()){
            return uObject.get();
        }else{
            return null;
        }
    }
}
