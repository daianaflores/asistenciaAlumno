package asistenciaalumnos.app.service;

import asistenciaalumnos.app.model.Alumno;
import asistenciaalumnos.app.model.Asistencia;
import asistenciaalumnos.app.model.Cursada;
import asistenciaalumnos.app.model.DTO.AsistenciaDto;

import java.util.Date;
import java.util.List;

public interface AsistenciaService {
    String getHello() throws Exception;

    List<AsistenciaDto> getAsistencias() throws Exception;

    //Asistencia tomaAsistencia(Asistencia asistencia) throws Exception;

    Asistencia modificacionAsistencia(Asistencia asistencia,Date fecha) throws Exception ;

    Asistencia altaAsistencia (Asistencia asistencia, Date fecha) throws  Exception ;

    void bajaAsistencia(Asistencia asistencia) throws Exception;

    List<AsistenciaDto> findAsistenciasByFechaAndCursada(Cursada cursada, Date fecha);
}
