package asistenciaalumnos.app.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AsistenciaAlumnoId implements Serializable {

    @Column(name= "ID_ALUMNO")
    private Long alumnoId;

    @Column(name = "ID_ASISTENCIA")
    private Long asistenciaId;

    public AsistenciaAlumnoId(Long alumnoId,Long  asistenciaId ){
        this.alumnoId = alumnoId;
        this.asistenciaId = asistenciaId;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Long getAsistenciaId() {
        return asistenciaId;
    }

    public void setAsistenciaId(Long asistenciaId) {
        this.asistenciaId = asistenciaId;
    }

    public AsistenciaAlumnoId(){ }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        AsistenciaAlumnoId that = (AsistenciaAlumnoId) o;
        return Objects.equals(alumnoId, that.alumnoId) &&
                Objects.equals(asistenciaId, that.asistenciaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumnoId, asistenciaId);
    }
}
