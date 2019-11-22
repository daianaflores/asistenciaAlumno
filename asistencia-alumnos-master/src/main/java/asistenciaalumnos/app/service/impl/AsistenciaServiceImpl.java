package asistenciaalumnos.app.service.impl;

import asistenciaalumnos.app.model.Alumno;
import asistenciaalumnos.app.model.Asistencia;
import asistenciaalumnos.app.model.AsistenciaAlumno;
import asistenciaalumnos.app.model.Cursada;
import asistenciaalumnos.app.model.Estado;
import asistenciaalumnos.app.model.DTO.AsistenciaDto;
import asistenciaalumnos.app.repository.AsistenciaAlumnoRepository;
import asistenciaalumnos.app.repository.AsistenciaRepository;
import asistenciaalumnos.app.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class  AsistenciaServiceImpl implements AsistenciaService {

    private static final Long DADO_DE_BAJA = 1L;

	@Autowired
	AsistenciaRepository asistenciaRepository;

	@Autowired
    AsistenciaAlumnoRepository asistenciaAlumnoRepository;


	//xq se sigue usando el metodo helloWorld?
    @Override
    public String getHello() throws Exception    {
        String message = "Hello World from API REST Spring Boot!!";
        return message;
    }

    @Override
    public List<AsistenciaDto> getAsistencias() throws Exception {
        List<Asistencia> asistencias = asistenciaRepository.findAll();
        List<AsistenciaDto> asistenciaDtoList = asistencias.stream().map(i -> new AsistenciaDto(i)).collect(Collectors.toList());
        return asistenciaDtoList;
    }

    @Override
    //seguir desarrollando -->se debería poder modificar los presentes una vez grabados?
    public Asistencia modificacionAsistencia(Asistencia asistencia,Date fecha) throws Exception{
        Asistencia asistenciaDB = findById(asistencia.getId());
        mergeObjects(asistenciaDB,asistencia);
        bindProperties(asistencia,fecha);
        save(asistenciaDB);
        return asistenciaDB;
    }

    @Override
    //creo nuevo objeto asiatencia con lo q me llega del front end --revisar probar funcionamiento
    public Asistencia altaAsistencia(Asistencia aObject, Date fecha ) throws Exception {
        List<Alumno> alumnoList = new ArrayList<>();
        List<Alumno> ausAlumnoList = new ArrayList<>();
           for(AsistenciaAlumno a : aObject.getAsistenciaAlumnos()){
               Alumno it = a.getAlumno();
               if(a.getPresent() == true) {
                   alumnoList.add(it);
               }else{
                   ausAlumnoList.add(it);
               }
           }
        aObject.setAsistenciaAlumnos(null);
        bindProperties(aObject,fecha);
        save(aObject);
        asistenciaAlumnoRepository.saveAll(createNewAsistenciaAlumno(aObject,alumnoList,ausAlumnoList));
        return aObject;
    }

    public void save(Asistencia aObject){
        asistenciaRepository.save(aObject);
    }

    @Override
    //hacer borrado logico con seteo de columna en estado 'dado de baja'
    public Asistencia bajaAsistencia(Asistencia aObject) throws Exception {
        boolean asistenciaExist = asistenciaRepository.existsById(aObject.getId());
        if (!asistenciaExist) {
            return null;
        }
        Estado estadoBaja = new Estado();
        estadoBaja.setId(DADO_DE_BAJA);
        estadoBaja.setDescripcion("DESHABILITADO");
        aObject.setEstado(estadoBaja);
        return asistenciaRepository.saveAndFlush(aObject);
    }

    @Override
    public List<AsistenciaDto> findAsistenciasByFechaAndCursada(Cursada cursada, Date fecha) {
        List<Asistencia> asistencias = asistenciaRepository.findAsistenciasByFechaAndCursada(cursada.getId(),fecha);
        List<AsistenciaDto> asistenciaDtoList = asistencias.stream().map(i -> new AsistenciaDto(i)).collect(Collectors.toList());
        return asistenciaDtoList;

    }


    public Asistencia findById(Long id){
        Optional<Asistencia> asistencia = asistenciaRepository.findById(id);
        if(asistencia.isPresent()){
            return asistencia.get();
        }else{
            return null;
        }
    }


   public Set<AsistenciaAlumno> createNewAsistenciaAlumno(Asistencia aObject, List<Alumno> alumnoList,List<Alumno> ausAlumnoList){
        Set<AsistenciaAlumno> set = new HashSet<>();
        for(Alumno alumno: alumnoList){
            AsistenciaAlumno asistenciaAlumno = new AsistenciaAlumno(aObject,alumno);
            asistenciaAlumno.setPresent(true);
            set.add(asistenciaAlumno);
        }
       for(Alumno alumno: ausAlumnoList){
           AsistenciaAlumno asistenciaAlumno = new AsistenciaAlumno(aObject,alumno);
           asistenciaAlumno.setPresent(false);
           set.add(asistenciaAlumno);
       }
        return set;
    }

    public Asistencia bindProperties(Asistencia aObject, Date currentDate){
        if(aObject.getId() != null ){
            aObject.setAuditableUpdate(currentDate,"logged usser");
        }else{
            aObject.setAuditable(currentDate,"logged usser");
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
