package asistenciaalumnos.app.service;

import asistenciaalumnos.app.configs.UserDetails;
import asistenciaalumnos.app.model.DTO.MateriaDto;
import asistenciaalumnos.app.model.Materia;
import asistenciaalumnos.app.repository.MateriaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MateriaService {

    @Autowired
    MateriaRepository materiaRepository;

    private static final Long DADO_DE_BAJA = 1L;

    private static  final Logger LOGGER = LogManager.getLogger(CursadaService.class);

    public Materia findById(Long id){
        Optional<Materia> materia = materiaRepository.findById(id);
        if(materia.isPresent()){
            return materia.get();
        }else{
            return null;
        }
    }

    public List<MateriaDto> findAll(){
        List<Materia> cList = materiaRepository.findAll();
        List<MateriaDto> cursadaDtoList = cList.stream().map(it -> new MateriaDto(it)).collect(Collectors.toList());
        return cursadaDtoList;
    }

    public void save(Materia materia){materiaRepository.save(materia);}

    //metodo para setear los campos de auditoría
    public Materia bindProperties(Materia mObject, UserDetails user, Date currentDate){
        if(mObject.getId() != null ){
            mObject.setAuditableUpdate(currentDate,user.getUsername());
        }else{
            mObject.setAuditable(currentDate,user.getUsername());
        }
        return mObject;
    }

    public void updateMateria(Materia mObject,UserDetails user){
        Materia materiadb = findById(mObject.getId());
        mergeObjects(materiadb,mObject);
        bindProperties(materiadb,user,new Date());
        save(materiadb);
    }

    public Materia mergeObjects(Materia materiaDb,Materia mObject){
        materiaDb.setDescripcion(mObject.getDescripcion());
        return materiaDb;

    }

    public void bajaMateria(Materia mObject) throws Exception{
        mObject.getEstado().setId(DADO_DE_BAJA);
    }
}
