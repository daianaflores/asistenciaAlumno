package asistenciaalumnos.app.repository;

import asistenciaalumnos.app.model.AsistenciaAlumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsistenciaAlumnoRepository extends JpaRepository<AsistenciaAlumno, Long> {
}
