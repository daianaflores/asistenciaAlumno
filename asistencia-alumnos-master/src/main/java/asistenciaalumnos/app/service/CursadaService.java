package asistenciaalumnos.app.service;

import asistenciaalumnos.app.configs.UserDetails;
import asistenciaalumnos.app.model.Alumno;
import asistenciaalumnos.app.model.Cursada;
import asistenciaalumnos.app.model.DTO.CursadaDto;
import asistenciaalumnos.app.repository.CursadaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CursadaService {

    @Autowired
    CursadaRepository cursadaRepository;

    private static  final Logger LOGGER = LogManager.getLogger(CursadaService.class);

    public Cursada findById(Long id){
        Optional<Cursada> cursada = cursadaRepository.findById(id);
        if(cursada.isPresent()){
            return cursada.get();
        }else{
            return null;
        }
    }

    public List<CursadaDto> findAll(){
        List<Cursada> cList = cursadaRepository.findAll();
        List<CursadaDto> cursadaDtoList = cList.stream().map(it -> new CursadaDto(it)).collect(Collectors.toList());
        return cursadaDtoList;
    }
    @Transactional
    public void updateCursada(Cursada cObject, UserDetails user){
        //me traigo la cursada ya grabada para modificar sus propiedades y luego lo vuelvo a grabar

        Cursada cursadaDb = findById(cObject.getId());
        List<Alumno> cursadaDbAlumnos = new ArrayList<>(cursadaDb.getAlumnos());
        List<Alumno> cObjectAlumnosList = new ArrayList<>(cObject.getAlumnos());
        List<Alumno> nonExistinAlumnos = new ArrayList<>();

        /*comparo listas de alumnos en Cursada ya persistidas con las que me llegan del front end para
        saber si se agregaron o quitaron alumnos de dicha lista*/
        List<Alumno> added = cObjectAlumnosList.stream().filter(a -> cursadaDbAlumnos.stream().noneMatch(dbA -> dbA.getMatricula().equals(a.getMatricula()))).collect(Collectors.toList());
        List<Alumno> removed= cursadaDbAlumnos.stream().filter(dbA -> cObjectAlumnosList.stream().noneMatch(a -> a.getMatricula().equals(dbA.getMatricula()))).collect(Collectors.toList());
        mergeObjects(cursadaDb,cObject);
        bindProperties(cursadaDb,user,new Date());
        /*reviso las listas y agrego o saco alumnos según sea necesario*/
        if(added.isEmpty() && removed.isEmpty()){
            save(cursadaDb);
        }else {
            if (!added.isEmpty()) {
                for (Alumno a : added) {
                    if (cursadaDbAlumnos.contains(a)) {
                        LOGGER.info("El alumno ya se encuentra dado de alta en la cursada");
                    } else {
                        nonExistinAlumnos.add(a);
                        LOGGER.info("se agregó al alumno/a con número de matricula:" + a.getMatricula() + " a la cursada");
                    }
                }
                if (!nonExistinAlumnos.isEmpty()) {
                    Set toSet = new HashSet(nonExistinAlumnos);
                    cursadaDb.setAlumnos(toSet);
                    save(cursadaDb);
                }
            }
            if (!removed.isEmpty()) {
                for (Alumno a : removed) {
                    cursadaDb.getAlumnos().remove(a);
                    LOGGER.info("se removió al alumno/a con número de matricula:" + a.getMatricula() + " de la cursada");
                }
                save(cursadaDb);
            }
        }
    }


    //grabar objeto en base de datos
    public void save(Cursada cursada){cursadaRepository.save(cursada);}

    //metodo para setear los campos de auditoría
    public Cursada bindProperties(Cursada cObject, UserDetails user, Date currentDate){
        if(cObject.getId() != null ){
            cObject.setAuditableUpdate(currentDate,user.getUsername());
        }else{
            cObject.setAuditable(currentDate,user.getUsername());
        }
        return cObject;
    }

    //seteo proiedades que llegan desde el front end a mano
    public Cursada mergeObjects(Cursada cursadaDb,Cursada cObject){
        cursadaDb.setAlumnos(cObject.getAlumnos());
        cursadaDb.setCuatrimestre(cObject.getCuatrimestre());
        cursadaDb.setDocente(cObject.getDocente());
        cursadaDb.setEstado(cObject.getEstado());
        cursadaDb.setEstado(cObject.getEstado());
        cursadaDb.setMateria(cObject.getMateria());
        cursadaDb.setYear(cObject.getYear());
        return cursadaDb;

    }

}
