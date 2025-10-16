package co.edu.unicauca.degreeprojectmicroservicescommunication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "estudiante")
@PrimaryKeyJoinColumn(name = "persona_id")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estudiante extends Persona {
    @Column(length = 30, nullable = false, unique = true)
    private String codigoEstudiante;

    @ManyToMany(mappedBy = "estudiantes")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<TrabajoDeGrado> trabajos;


    private Programa programa;

    @EqualsAndHashCode.Include
    public Long getId() {
        return super.getId();
    }
}
