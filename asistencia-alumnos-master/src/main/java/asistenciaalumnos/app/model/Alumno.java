package asistenciaalumnos.app.model;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Alumnos")

public class Alumno extends Auditable<String>{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;

	@Column(name = "apellido", length = 30, nullable = false)
	private String apellido;

	@Column(name = "email", length = 100, nullable = false)
	private String email;

	@Column(name = "fechaNacimiento", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;

	@Column(name = "DNI", nullable = false)
	private Long DNI;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ESTADO", nullable = true)
	private Estado estado;

	@OneToOne(orphanRemoval=true)
	@JoinColumn(name = "ID_CONTACTO", referencedColumnName = "ID", nullable = true)
	private Contacto contacto;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "CursadaAlumno",
			joinColumns = { @JoinColumn(name = "ID_ALUMNO") },
			inverseJoinColumns = { @JoinColumn(name = "ID_CURSADA") }
	)

	private Set<Cursada> cursadaAlumnos = new HashSet<>();

	@OneToMany(mappedBy = "alumno",cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	private Set<AsistenciaAlumno> asistenciaAlumnos = new HashSet<>();

	@PrePersist
	protected void onCreate() {
		this.creationDate = new Date();
		this.lastModifiedDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.lastModifiedDate = new Date();
	}

	public Alumno()
	{
		super();
	}

	public Long getDNI() {
		return DNI;
	}

	public void setDNI(Long DNI) {
		this.DNI = DNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

/*	 public Cursada getCursada() {
	 	return cursada;
	 }

	 public void setCursada(Cursada cursada) {
	 	this.cursada = cursada;
	 }*/

	public Set<Cursada> getCursadaAlumnos() {
		return cursadaAlumnos;
	}

	public void setCursadaAlumnos(Set<Cursada> cursadaAlumnos) {
		this.cursadaAlumnos = cursadaAlumnos;
	}

	public Set<AsistenciaAlumno> getAsistenciaAlumnos() {
		return asistenciaAlumnos;
	}

	public void setAsistenciaAlumnos(Set<AsistenciaAlumno> asistenciaAlumnos) {
		this.asistenciaAlumnos = asistenciaAlumnos;
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
		if (!(obj instanceof Alumno))
			return false;
		Alumno other = (Alumno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}