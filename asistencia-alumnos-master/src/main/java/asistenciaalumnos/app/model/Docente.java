package asistenciaalumnos.app.model;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "USUARIO")
public class Docente extends Usuario{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    //dejo clase vacía por las dudas revisar como inicializar
    @PrePersist
    //seteo al crear q siempre va a ser un usuario de tipo docente a partir del id de tipoUsuario
    protected void onCreate() {
        this.getTipoUsuario().setId(2L);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Docente()
    {
        super();
    }


}
