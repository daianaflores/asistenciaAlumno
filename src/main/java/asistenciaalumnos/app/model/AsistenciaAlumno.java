package asistenciaalumnos.app.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "AsistenciaAlumno")
@Table(name = "AsistenciaAlumno")
public class AsistenciaAlumno extends Auditable<String> {

   @EmbeddedId
   private AsistenciaAlumnoId id;

    @Column(name = "IS_PRESENT")
    private Boolean isPresent = false;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="ID_ALUMNO",referencedColumnName = "ID",nullable = true)
    @MapsId("alumnoId")
    //@Column(name = "ID_ALUMNO")
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="ID_ASISTENCIA",referencedColumnName = "ID",nullable = true)
    @MapsId("asistenciaId")
    private Asistencia asistencia;

    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
        this.lastModifiedDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedDate = new Date();
    }

    public AsistenciaAlumno(){}

    public AsistenciaAlumno(Asistencia asistencia, Alumno alumno){
        this.alumno = alumno;
        this.asistencia = asistencia;
        this.id = new AsistenciaAlumnoId(alumno.getId(),asistencia.getId());
    }

    public AsistenciaAlumnoId getId() {
        return id;
    }

    public void setId(AsistenciaAlumnoId id) {
        this.id = id;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        AsistenciaAlumno that = (AsistenciaAlumno) o;
        return Objects.equals(alumno, that.alumno) &&
                Objects.equals(asistencia, that.asistencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumno, asistencia);
    }
}
