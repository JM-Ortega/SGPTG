package co.edu.unicauca.degreeprojectmicroservicescommunication.repository;

import co.edu.unicauca.degreeprojectmicroservicescommunication.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
