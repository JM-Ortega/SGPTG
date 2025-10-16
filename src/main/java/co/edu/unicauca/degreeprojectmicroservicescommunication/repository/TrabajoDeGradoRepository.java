package co.edu.unicauca.degreeprojectmicroservicescommunication.repository;

import co.edu.unicauca.degreeprojectmicroservicescommunication.entity.TrabajoDeGrado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajoDeGradoRepository extends JpaRepository<TrabajoDeGrado, Long> {

    long countByDirector_Correo(String correo);
    long countByCodirector_Correo(String correo);
}
