package co.edu.unicauca.degreeprojectmicroservicescommunication.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "trabajo_de_grado")
public class TrabajoDeGrado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToMany
    @JoinTable(
        name = "trabajo_estudiantes",
        joinColumns = @JoinColumn(name = "trabajo_id"),
        inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    @com.fasterxml.jackson.annotation.JsonIgnore

    private List<Estudiante> estudiantes;

    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private Docente director;

    @ManyToOne
    @JoinColumn(name = "codirector_id")
    private Docente codirector;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "anteproyecto_id")
    private Anteproyecto anteproyecto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_trabajo", nullable = false)
    private TipoTrabajoGrado tipoTrabajoGrado;

    public TrabajoDeGrado() {}

    public Long getId() { return id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public List<Estudiante> getEstudiantes() { return estudiantes; }
    public void setEstudiantes(List<Estudiante> estudiantes) { this.estudiantes = estudiantes; }

    public Docente getDirector() { return director; }
    public void setDirector(Docente director) { this.director = director; }

    public Docente getCodirector() { return codirector; }
    public void setCodirector(Docente codirector) { this.codirector = codirector; }

    public Anteproyecto getAnteproyecto() { return anteproyecto; }
    public void setAnteproyecto(Anteproyecto anteproyecto) { this.anteproyecto = anteproyecto; }

    public TipoTrabajoGrado getTipoTrabajoGrado() { return tipoTrabajoGrado; }
    public void setTipoTrabajoGrado(TipoTrabajoGrado tipoTrabajoGrado) { this.tipoTrabajoGrado = tipoTrabajoGrado; }
}
