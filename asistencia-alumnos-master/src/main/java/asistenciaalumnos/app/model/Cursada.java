package asistenciaalumnos.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "CURSADA")
public class Cursada extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @OneToOne(orphanRemoval=true)
    @JoinColumn(name = "ID_MATERIA", referencedColumnName = "ID",nullable = true)
    private Materia materia;

    @ManyToMany(mappedBy = "cursadaAlumnos")
    private Set<Alumno> cursadaAlumnos = new HashSet<>();

    //va a tabla tipo Docente o Usuario? no se entiende el script de DB
    @OneToOne(orphanRemoval=true)
    @JoinColumn(name = "ID_DOCENTE", referencedColumnName = "ID" ,nullable = true)
    private Docente docente;

    @OneToOne(orphanRemoval=true)
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID", nullable = true)
    private Estado estado;

    @Column(name = "ANIO", nullable = false)
    private Integer year;

    @Column(name = "CUATRIMESTRE", nullable = false)
    private Integer cuatrimestre;

    public Cursada()
    {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Set<Alumno> getCursadaAlumnos() {
        return cursadaAlumnos;
    }

    public void setCursadaAlumnos(Set<Alumno> cursadaAlumnos) {
        this.cursadaAlumnos = cursadaAlumnos;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(Integer cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
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
        if (!(obj instanceof Cursada))
            return false;
        Cursada other = (Cursada) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
