package asistenciaalumnos.app.service.impl;

import asistenciaalumnos.app.Dao.UsuarioDAO;
import asistenciaalumnos.app.model.Administrativo;
import asistenciaalumnos.app.model.DTO.AdministrativoDTO;
import asistenciaalumnos.app.model.DTO.DocenteDto;
import asistenciaalumnos.app.model.DTO.UsuarioDto;
import asistenciaalumnos.app.model.Docente;
import asistenciaalumnos.app.model.Usuario;
import asistenciaalumnos.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioDAO usuarioDAO;

    @Override
    public String getHello() throws Exception
    {
        String message = "Hello World from API REST Spring Boot!!";
        return message;
    }

    @Override
    public List<UsuarioDto> getUsuarios() throws Exception
    {
        List<Usuario> usuarios = usuarioDAO.findAll();
        List<UsuarioDto> UsuarioDtoList = usuarios.stream().map(it -> new UsuarioDto(it)).collect(Collectors.toList());

        return UsuarioDtoList;
    }

    public List<DocenteDto> getDocentes() throws Exception
    {
        List<Docente> docentes = usuarioDAO.findDocentes();
        List<DocenteDto> docenteDtoList = docentes.stream().map(it -> new DocenteDto(it)).collect(Collectors.toList());

        return docenteDtoList;
    }

    public List<AdministrativoDTO> getAdministrativos() throws Exception
    {
        List<Administrativo> administrativos = usuarioDAO.findAdministrativos();
        List<AdministrativoDTO> admDtoList = administrativos.stream().map(it -> new AdministrativoDTO(it)).collect(Collectors.toList());

        return admDtoList;
    }

    public Usuario findById(Long id)throws Exception{
        Usuario usuario = null;
        return usuario = usuarioDAO.findOne(id);
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
    public int altaUsuario(Usuario usuario) throws Exception
    {
        Integer usuarioResponse = usuarioDAO.insert(usuario);
        return usuarioResponse;
    }

    @Override
    public int modificacionUsuario(Usuario usuario) throws Exception
    {
        Integer afectedRegister = new Integer(0);
        Usuario usurioExist = usuarioDAO.findOne(usuario.getDni());
        if (usurioExist != null)
        {
            afectedRegister = usuarioDAO.insert(usuario);
            return afectedRegister;
        }
        return afectedRegister;
    }

    @Override
    public int bajaUsuario(Long id) throws Exception
    {
        Integer afectedRegister = new Integer(0);
        afectedRegister = usuarioDAO.delete(id);
        return afectedRegister;
    }
}
