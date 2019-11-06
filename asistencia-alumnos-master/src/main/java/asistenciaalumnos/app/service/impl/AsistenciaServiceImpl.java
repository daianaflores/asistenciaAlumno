package asistenciaalumnos.app.service.impl;


import java.util.*;
import java.util.stream.Collectors;

import asistenciaalumnos.app.configs.CurrentUser;
import asistenciaalumnos.app.configs.UserDetails;
import asistenciaalumnos.app.model.Alumno;
import asistenciaalumnos.app.model.AsistenciaAlumno;
import asistenciaalumnos.app.model.Cursada;
import asistenciaalumnos.app.model.DTO.AsistenciaDto;
import asistenciaalumnos.app.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import asistenciaalumnos.app.model.Asistencia;
import asistenciaalumnos.app.repository.AsistenciaRepository;
import javax.transaction.Transactional;


@Service
@Transactional
public class  AsistenciaServiceImpl implements AsistenciaService {

    private static final Long DADO_DE_BAJA = 1L;

	@Autowired
	AsistenciaRepository asistenciaRepository;


	//xq se sigue usando el metodo helloWorld?
    @Override
    public String getHello() throws Exception
    {
        String message = "Hello World from API REST Spring Boot!!";
        return message;
    }

    @Override
    public List<AsistenciaDto> getAsistencias() throws Exception
    {
        List<Asistencia> asistencias = asistenciaRepository.findAll();
        List<AsistenciaDto> asistenciaDtoList = asistencias.stream().map(i -> new AsistenciaDto(i)).collect(Collectors.toList());

        return asistenciaDtoList;
    }

    @Override
    //seguir desarrollando
    public Asistencia modificacionAsistencia(Asistencia asistencia,UserDetails user,Date fecha) throws Exception{
        Asistencia asistenciaDB = findById(asistencia.getId());
        mergeObjects(asistenciaDB,asistencia);
        bindProperties(asistencia,user,fecha);
        save(asistenciaDB);
        return asistenciaDB;
    }

    @Override
    //creo nuevo objeto asiatencia con lo q me llega del front end --revisar probar funcionamiento
    public Asistencia altaAsistencia(Cursada cursada, List<Alumno> alumnoList, Date fecha, UserDetails user) throws Exception {
        Asistencia aObject = new Asistencia();
        aObject.setAsistenciaAlumnos(createNewAsistenciaAlumno(aObject,alumnoList));
        bindProperties(aObject,user,fecha);
        save(aObject);
        return aObject;
    }

    public void save(Asistencia aObject){
        asistenciaRepository.save(aObject);
    }

    @Override
    //hacer borrado logico con seteo de columna en estado 'dado de baja'
    public void bajaAsistencia(Asistencia aObject) throws Exception {
        aObject.getEstado().setId(DADO_DE_BAJA);


    }


    public Asistencia findById(Long id){
        Optional<Asistencia> asistencia = asistenciaRepository.findById(id);
        if(asistencia.isPresent()){
            return asistencia.get();
        }else{
            return null;
        }
    }

    public Set<AsistenciaAlumno> createNewAsistenciaAlumno(Asistencia aObject,List<Alumno> alumnoList){
        Set<AsistenciaAlumno> set = new HashSet<>();
        for(Alumno alumno: alumnoList){
            AsistenciaAlumno asistenciaAlumno = new AsistenciaAlumno();
            asistenciaAlumno.setAlumno(alumno);
            asistenciaAlumno.setAsistencia(aObject);
            set.add(asistenciaAlumno);
        }
        return set;
    }

    public Asistencia bindProperties(Asistencia aObject, UserDetails user, Date currentDate){
        if(aObject.getId() != null ){
            aObject.setAuditableUpdate(currentDate,user.getUsername());
        }else{
            aObject.setAuditable(currentDate,user.getUsername());
        }
        return aObject;
    }

    public Asistencia mergeObjects(Asistencia asistenciaDb,Asistencia aObject){
        asistenciaDb.setEstado(aObject.getEstado());
        asistenciaDb.setCursada(aObject.getCursada());
        asistenciaDb.setFecha(aObject.getFecha());
        asistenciaDb.setAsistenciaAlumnos(aObject.getAsistenciaAlumnos());
        return asistenciaDb;
    }
}
