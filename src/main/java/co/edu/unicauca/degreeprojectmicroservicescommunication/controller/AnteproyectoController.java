package co.edu.unicauca.degreeprojectmicroservicescommunication.controller;

import co.edu.unicauca.degreeprojectmicroservicescommunication.entity.Anteproyecto;
import co.edu.unicauca.degreeprojectmicroservicescommunication.repository.AnteproyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/anteproyectos")
public class AnteproyectoController {
    @Autowired
    private AnteproyectoRepository anteproyectoRepository;

    @GetMapping
    public List<Anteproyecto> getAllAnteproyectos() {
        return anteproyectoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Anteproyecto getAnteproyectoById(@PathVariable Long id) {
        return anteproyectoRepository.findById(id).orElse(null);
    }
}
