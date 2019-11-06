package asistenciaalumnos.app.service;

import java.util.List;

import asistenciaalumnos.app.model.Materia;
import asistenciaalumnos.app.model.DTO.MateriaDto;

public interface MateriaService
{
    String getHello() throws Exception;

    List<AlumnoDto> getMateria() throws Exception;

    Alumno altaMateria(Materia materia) throws Exception;

    Alumno modificacionMateria(Materia materia) throws Exception ;

    void bajaMateria(Long id) throws Exception;
}