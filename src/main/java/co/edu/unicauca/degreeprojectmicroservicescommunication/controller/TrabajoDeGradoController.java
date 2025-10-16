package co.edu.unicauca.degreeprojectmicroservicescommunication.controller;

import co.edu.unicauca.degreeprojectmicroservicescommunication.entity.TrabajoDeGrado;
import co.edu.unicauca.degreeprojectmicroservicescommunication.repository.TrabajoDeGradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trabajos")
public class TrabajoDeGradoController {
    @Autowired
    private TrabajoDeGradoRepository trabajoGradoRepository;

    @GetMapping
    public List<TrabajoDeGrado> getAllTrabajos() {
        return trabajoGradoRepository.findAll();
    }

    @GetMapping("/{id}")
    public TrabajoDeGrado getTrabajoById(@PathVariable Long id) {
        return trabajoGradoRepository.findById(id).orElse(null);
    }
}
