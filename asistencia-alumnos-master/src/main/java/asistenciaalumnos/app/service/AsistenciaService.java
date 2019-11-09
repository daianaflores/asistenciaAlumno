package asistenciaalumnos.app.service;

import java.util.Date;
import java.util.List;

import asistenciaalumnos.app.configs.UserDetails;
import asistenciaalumnos.app.model.Alumno;
import asistenciaalumnos.app.model.Asistencia;
import asistenciaalumnos.app.model.Cursada;
import asistenciaalumnos.app.model.DTO.AsistenciaDto;

public interface AsistenciaService {
    String getHello() throws Exception;

    List<AsistenciaDto> getAsistencias() throws Exception;

    //Asistencia tomaAsistencia(Asistencia asistencia) throws Exception;

    Asistencia modificacionAsistencia(Asistencia asistencia,UserDetails user,Date fecha) throws Exception ;

    Asistencia altaAsistencia (Cursada cursada, List<Alumno> alumnoList, Date fecha, UserDetails user) throws  Exception ;

    void bajaAsistencia(Asistencia asistencia) throws Exception;

    List<AsistenciaDto> findAsistenciasByFechaAndCursada(Cursada cursada, Date fecha);
}
