package co.edu.unicauca.degreeprojectmicroservicescommunication.controller;


import co.edu.unicauca.degreeprojectmicroservicescommunication.entity.Persona;
import co.edu.unicauca.degreeprojectmicroservicescommunication.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Persona getPersonaById(@PathVariable long id) {
        return personaRepository.findById(id).orElse(null);
    }
}
