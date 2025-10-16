package co.edu.unicauca.degreeprojectmicroservicescommunication.controller;

import co.edu.unicauca.degreeprojectmicroservicescommunication.entity.Docente;
import co.edu.unicauca.degreeprojectmicroservicescommunication.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/docentes")
public class DocenteController {
    @Autowired
    private DocenteRepository docenteRepository;

    @GetMapping
    public List<Docente> getAllProfesores() {
        return docenteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Docente getProfesorById(@PathVariable long id) {
        return docenteRepository.findById(id).orElse(null);
    }
}
