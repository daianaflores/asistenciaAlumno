package asistenciaalumnos.app.service.impl;

import asistenciaalumnos.app.model.Alumno;
import asistenciaalumnos.app.model.DTO.AlumnoDto;
import asistenciaalumnos.app.repository.AlumnoRepository;
import asistenciaalumnos.app.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceImpl implements AlumnoService
{
    @Autowired
    AlumnoRepository alumnoRepository;


    public Alumno findById(Long id){
        Optional<Alumno> alumno = alumnoRepository.findById(id);
        if(alumno.isPresent()){
            return alumno.get();
        }else{
            return null;
        }
    }

    @Override
    public String getHello() throws Exception
    {
        String message = "Hello World from API REST Spring Boot!!";
        return message;
    }

    @Override
    public List<AlumnoDto> getAlumnos() throws Exception
    {
        List<Alumno> alumnos = alumnoRepository.findAll();
        List<AlumnoDto> AlumnoDtoList = alumnos.stream().map(it -> new AlumnoDto(it)).collect(Collectors.toList());
        return AlumnoDtoList;
    }

    @Override
    public Alumno altaAlumno(Alumno alumno) throws Exception 
    {
        Alumno alumnoResponse = alumnoRepository.saveAndFlush(alumno);
        return alumnoResponse;
    }

    @Override
    public Alumno modificacionAlumno(Alumno alumno) throws Exception {
        boolean alumnoExist = alumnoRepository.existsById(alumno.getId());
        if (!alumnoExist){
            return null;
        }
        Alumno alumnoResponse = alumnoRepository.saveAndFlush(alumno);
        return alumnoResponse;
    }

    @Override
    public void bajaAlumno(Long id) throws Exception  {
        alumnoRepository.deleteById(id);
    }



}