package asistenciaalumnos.app.repository;

import asistenciaalumnos.app.model.Cursada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursadaRepository extends JpaRepository<Cursada, Long> {

}
