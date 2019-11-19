package asistenciaalumnos.app.repository;

import asistenciaalumnos.app.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long>
{
    
}