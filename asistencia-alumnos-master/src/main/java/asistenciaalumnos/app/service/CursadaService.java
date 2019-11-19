package asistenciaalumnos.app.service;

import asistenciaalumnos.app.model.Cursada;
import asistenciaalumnos.app.model.DTO.CursadaDto;
import asistenciaalumnos.app.repository.CursadaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    public void updateCursada(Cursada cObject, String user){

    }


    //grabar objeto en base de datos
    public void save(Cursada cursada){cursadaRepository.save(cursada);}

    //metodo para setear los campos de auditoría
    public Cursada bindProperties(Cursada cObject, String user, Date currentDate){
        if(cObject.getId() != null ){
            cObject.setAuditableUpdate(currentDate,"logged usser");
        }else{
            cObject.setAuditable(currentDate,"logged usser");
        }
        return cObject;
    }

    //seteo proiedades que llegan desde el front end a mano
    public Cursada mergeObjects(Cursada cursadaDb,Cursada cObject){
        //cursadaDb.setAlumnos(cObject.getAlumnos());
        cursadaDb.setCuatrimestre(cObject.getCuatrimestre());
        cursadaDb.setDocente(cObject.getDocente());
        cursadaDb.setEstado(cObject.getEstado());
        cursadaDb.setEstado(cObject.getEstado());
        cursadaDb.setMateria(cObject.getMateria());
        cursadaDb.setYear(cObject.getYear());
        return cursadaDb;

    }

    public Cursada newCursada(Cursada cObject,String usser,Date fecha){
       // bindProperties(cObject,usser,fecha);
        save(cObject);
        return cObject;
    }

}
