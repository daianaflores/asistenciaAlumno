package asistenciaalumnos.app.repository;

import asistenciaalumnos.app.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    @Query("SELECT a FROM Asistencia a join fetch Cursada c  WHERE c.id = :cursadaId and a.fecha = :fecha")
    List<Asistencia> findAsistenciasByFechaAndCursada(@Param("cursadaId") Long cursadaId,
                                                      @Param("fecha") Date fecha);


                                                    
}
