/*package asistenciaalumnos.app.model;

import javax.persistence.*;

import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "CursadaAlumno")
public class CursadaAlumno extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name="ID_ALUMNO",nullable = false)
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name="ID_CURSADA",nullable = true)
    private Cursada cursada;

    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
        this.lastModifiedDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedDate = new Date();
    }

    public CursadaAlumno(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Cursada getCursada() {
        return cursada;
    }

    public void setCursada(Cursada cursada) {
        this.cursada = cursada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof CursadaAlumno))
            return false;
        CursadaAlumno other = (CursadaAlumno) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
*/