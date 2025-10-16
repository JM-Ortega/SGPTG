package co.edu.unicauca.degreeprojectmicroservicescommunication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "docentes")
@PrimaryKeyJoinColumn(name = "persona_id")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Docente extends Persona {


    @Enumerated(EnumType.STRING)
    private Departamento departamento;
    @OneToMany(mappedBy = "director")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<TrabajoDeGrado> trabajosComoDirector;
    @OneToMany(mappedBy = "codirector")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<TrabajoDeGrado> trabajosComoCodirector;
    private String codigoDocente;

    @EqualsAndHashCode.Include
    public Long getId() {
        return super.getId();
    }
}
