package asistenciaalumnos.app.service;

import asistenciaalumnos.app.model.Alumno;
import asistenciaalumnos.app.model.DTO.AlumnoDto;

import java.util.List;

public interface AlumnoService
{
    String getHello() throws Exception;

    public Alumno findById(Long id) throws Exception;

    List<AlumnoDto> getAlumnos() throws Exception;

    Alumno altaAlumno(Alumno alumno) throws Exception;

    Alumno modificacionAlumno(Alumno alumno) throws Exception ;

    void bajaAlumno(Long id) throws Exception;
}