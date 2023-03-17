package com.persona.persona.controller;

import com.persona.persona.models.Persona;
import com.persona.persona.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/personas")
@CrossOrigin("*")
public class PersonaController {
    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/{id}")
    public Persona getPersona(@PathVariable Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaRepository.save(persona);
    }

    @PutMapping("/{id}")
    public Persona updatePersona(@PathVariable Long id, @RequestBody Persona persona) {
        Persona p = personaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        p.setNombre(persona.getNombre());
        p.setEdad(persona.getEdad());
        return personaRepository.save(p);
    }

    @DeleteMapping("/{id}")
    public void deletePersona(@PathVariable Long id) {
        personaRepository.deleteById(id);
    }
}
