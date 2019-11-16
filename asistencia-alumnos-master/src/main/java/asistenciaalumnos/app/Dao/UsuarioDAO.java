package asistenciaalumnos.app.Dao;

import asistenciaalumnos.app.model.*;
import asistenciaalumnos.app.repository.TipoUsuarioRepository;
import asistenciaalumnos.app.service.ContactoService;
import asistenciaalumnos.app.service.EstadoService;
import asistenciaalumnos.app.service.TipoUsuarioService;
import asistenciaalumnos.configuracionconeccion.Conexion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDAO implements GenericDAO<Usuario> {

    private static final Long ADMINISTRATIVO = 1L;
    private static final Long DOCENTE = 2L;
    @Autowired
    TipoUsuarioService tipoUsuarioService;

    @Autowired
    EstadoService estadoService;

    @Autowired
    ContactoService contactoService;

    public List<Usuario> findAll(){
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Connection con = Conexion.getConexion();

        try {
            PreparedStatement stmn = con.prepareStatement("select id, IdTipoUsuario, Nombre, Apellido, DNI, Fechanac, Legajo, Usuario, IdEstado, IdContacto from dbo.Usuario");

            ResultSet rs = stmn.executeQuery();
            Usuario usuario;
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setTipoUsuario(tipoUsuarioService.findById(rs.getLong("IdTipoUsuario")));
                usuario.setNombre(rs.getNString("Nombre"));
                usuario.setApellido(rs.getNString("Apellido"));
                usuario.setDni(rs.getLong("DNI"));
                usuario.setFechaDeNacimiento(rs.getDate("Fechanac"));
                usuario.setLegajo(rs.getLong("Legajo"));
                usuario.setUsser(rs.getNString("Usuario"));
                usuario.setEstado(estadoService.findById(rs.getLong("IdEstado")));
                usuario.setContacto(contactoService.findById(rs.getLong("IdContacto")));

               usuarios.add(usuario);
            }
            rs.close();
            stmn.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
          }

        return usuarios;
    }

    public List<Docente> findDocentes(){
        ArrayList<Docente> docentes= new ArrayList<Docente>();
        Connection con = Conexion.getConexion();

        try {
            PreparedStatement stmn = con.prepareStatement("select id, IdTipoUsuario, Nombre, Apellido, DNI, Fechanac, Legajo, Usuario, IdEstado, IdContacto from dbo.Usuario where IdTipoUsuario = 2");

            ResultSet rs = stmn.executeQuery();
            Docente docente;
            while (rs.next()) {
                docente = new Docente();
                docente.setId(rs.getLong("id"));
                docente.setTipoUsuario(tipoUsuarioService.findById(rs.getLong("IdTipoUsuario")));
                docente.setNombre(rs.getNString("Nombre"));
                docente.setApellido(rs.getNString("Apellido"));
                docente.setDni(rs.getLong("DNI"));
                docente.setFechaDeNacimiento(rs.getDate("Fechanac"));
                docente.setLegajo(rs.getLong("Legajo"));
                docente.setUsser(rs.getNString("Usuario"));
                docente.setEstado(estadoService.findById(rs.getLong("IdEstado")));
                docente.setContacto(contactoService.findById(rs.getLong("IdContacto")));

                docentes.add(docente);
            }
            rs.close();
            stmn.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return docentes;
    }


    public List<Administrativo> findAdministrativos(){
        ArrayList<Administrativo> administrativos= new ArrayList<Administrativo>();
        Connection con = Conexion.getConexion();

        try {
            PreparedStatement stmn = con.prepareStatement("select id, IdTipoUsuario, Nombre, Apellido, DNI, Fechanac, Legajo, Usuario, IdEstado, IdContacto from dbo.Usuario where IdTipoUsuario = 1");

            ResultSet rs = stmn.executeQuery();
            Administrativo administrativo;
            while (rs.next()) {
                administrativo = new Administrativo();
                administrativo.setId(rs.getLong("id"));
                administrativo.setTipoUsuario(tipoUsuarioService.findById(rs.getLong("IdTipoUsuario")));
                administrativo.setNombre(rs.getNString("Nombre"));
                administrativo.setApellido(rs.getNString("Apellido"));
                administrativo.setDni(rs.getLong("DNI"));
                administrativo.setFechaDeNacimiento(rs.getDate("Fechanac"));
                administrativo.setLegajo(rs.getLong("Legajo"));
                administrativo.setUsser(rs.getNString("Usuario"));
                administrativo.setEstado(estadoService.findById(rs.getLong("IdEstado")));
                administrativo.setContacto(contactoService.findById(rs.getLong("IdContacto")));

                administrativos.add(administrativo);
            }
            rs.close();
            stmn.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrativos;
    }


    public Usuario findOne(Long id){
        Connection con = Conexion.getConexion();
        Usuario usuario = null;
        try {
            PreparedStatement stmn = con.prepareStatement("select id, IdTipoUsuario, Nombre, Apellido, DNI, Fechanac, Legajo, Usuario, IdEstado, IdContacto from dbo.Usuario where matricula =" + id);

            ResultSet rs = stmn.executeQuery();
                usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setTipoUsuario(tipoUsuarioService.findById(rs.getLong("IdTipoUsuario")));
                usuario.setNombre(rs.getNString("Nombre"));
                usuario.setApellido(rs.getNString("Apellido"));
                usuario.setDni(rs.getLong("DNI"));
                usuario.setFechaDeNacimiento(rs.getDate("Fechanac"));
                usuario.setLegajo(rs.getLong("Legajo"));
                usuario.setUsser(rs.getNString("Usuario"));
                usuario.setEstado(estadoService.findById(rs.getLong("IdEstado")));
                usuario.setContacto(contactoService.findById(rs.getLong("IdContacto")));

            rs.close();
            stmn.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;

    }

    public Docente findOneDocente(Long id){
        Connection con = Conexion.getConexion();
        Docente docente = null;
        try {
            PreparedStatement stmn = con.prepareStatement("select id, IdTipoUsuario, Nombre, Apellido, DNI, Fechanac, Legajo, Usuario, IdEstado, IdContacto from dbo.Usuario where matricula =" + id +"and IdTipoUsuario = 2 ");

            ResultSet rs = stmn.executeQuery();
            docente = new Docente();
            docente.setId(rs.getLong("id"));
            docente.setTipoUsuario(tipoUsuarioService.findById(rs.getLong("IdTipoUsuario")));
            docente.setNombre(rs.getNString("Nombre"));
            docente.setApellido(rs.getNString("Apellido"));
            docente.setDni(rs.getLong("DNI"));
            docente.setFechaDeNacimiento(rs.getDate("Fechanac"));
            docente.setLegajo(rs.getLong("Legajo"));
            docente.setUsser(rs.getNString("Usuario"));
            docente.setEstado(estadoService.findById(rs.getLong("IdEstado")));
            docente.setContacto(contactoService.findById(rs.getLong("IdContacto")));

            rs.close();
            stmn.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return docente;

    }

    public Administrativo findOneAdministrativo(Long id){
        Connection con = Conexion.getConexion();
        Administrativo administrativo = null;
        try {
            PreparedStatement stmn = con.prepareStatement("select id, IdTipoUsuario, Nombre, Apellido, DNI, Fechanac, Legajo, Usuario, IdEstado, IdContacto from dbo.Usuario where matricula =" + id +"and IdTipoUsuario = 1 ");

            ResultSet rs = stmn.executeQuery();
            administrativo = new Administrativo();
            administrativo.setId(rs.getLong("id"));
            administrativo.setTipoUsuario(tipoUsuarioService.findById(rs.getLong("IdTipoUsuario")));
            administrativo.setNombre(rs.getNString("Nombre"));
            administrativo.setApellido(rs.getNString("Apellido"));
            administrativo.setDni(rs.getLong("DNI"));
            administrativo.setFechaDeNacimiento(rs.getDate("Fechanac"));
            administrativo.setLegajo(rs.getLong("Legajo"));
            administrativo.setUsser(rs.getNString("Usuario"));
            administrativo.setEstado(estadoService.findById(rs.getLong("IdEstado")));
            administrativo.setContacto(contactoService.findById(rs.getLong("IdContacto")));

            rs.close();
            stmn.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return administrativo;

    }

    public int insert(Usuario usuario) {
        Connection con = Conexion.getConexion();
        String sql = "INSERT INTO dbo.Usuario (id, IdTipoUsuario, Nombre, Apellido, DNI, Fechanac, Legajo, Usuario, Password, IdEstado, IdContacto ) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        int x = 0;
        try {
            PreparedStatement stmn = con.prepareStatement(sql);
            stmn.setLong(1, usuario.getId());
            stmn.setLong(2, usuario.getTipoUsuario().getId());
            stmn.setString(3, usuario.getNombre());
            stmn.setString(4, usuario.getApellido());
            stmn.setLong(5, usuario.getDni());
            stmn.setDate(6, (Date) usuario.getFechaDeNacimiento());
            stmn.setLong(7, usuario.getLegajo());
            stmn.setString(8, usuario.getUsser());
            stmn.setString(9, usuario.getPassword());
            stmn.setLong(10, usuario.getEstado().getId());
            stmn.setLong(11, usuario.getContacto().getId());

            x = stmn.executeUpdate();
            stmn.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return x;
    }

    public int delete(Long id) {
        String sql = "DELETE FROM dbo.Usuario WHERE DNI = ?";
        int x = 0;
        Connection con = Conexion.getConexion();
        try {
            PreparedStatement stmt =  con.prepareStatement(sql + id);
            stmt.setLong(1, id);
            x = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return x;
    }

    public int update(Usuario usuario) {
        //esta query esta mal revisar parametros y columnas correspondidas en la base (nombres)
        //  String sql = " Update Alumno set NRO_PERSONA, APELLIDO, NOMBRE, GENERO, EDAD, EMAIL = (?,?,?,?,?,?) where id = ? " ;
        String sql = "update dbo.Usuario set id =?, IdTipoUsuario=?, Nombre=?, Apellido=?, DNI=?, Fechanac=?, Legajo=?, Usuario=?, Password=?, IdEstado=?, IdContacto=?  where id = ?";
        int x = 0;
        Connection con = Conexion.getConexion();
        try {
            PreparedStatement stmn =  con.prepareStatement(sql);

            stmn.setLong(1, usuario.getId());
            stmn.setLong(2, usuario.getTipoUsuario().getId());
            stmn.setString(3, usuario.getNombre());
            stmn.setString(4, usuario.getApellido());
            stmn.setLong(5, usuario.getDni());
            stmn.setDate(6, (Date) usuario.getFechaDeNacimiento());
            stmn.setLong(7, usuario.getLegajo());
            stmn.setString(8, usuario.getUsser());
            stmn.setString(9, usuario.getPassword());
            stmn.setLong(10, usuario.getEstado().getId());
            stmn.setLong(11, usuario.getContacto().getId());

            x = stmn.executeUpdate();
            stmn.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return x;
    }



}
