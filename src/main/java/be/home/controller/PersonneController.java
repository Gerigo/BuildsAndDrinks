package be.home.controller;

import be.home.domain.Personne;
import be.home.repositories.IPersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
@RequestMapping("/")
public class PersonneController {

    private IPersonRepository repositories;

    public PersonneController(IPersonRepository repositories) {
        this.repositories = repositories;
    }

    @GetMapping()
    public String getAllPersonnes(Model m){
        m.addAttribute("liste",repositories.findAll());
        return "home";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> getOnePersonne(@PathVariable("id") String id){
        Optional<Personne> foundOne = repositories.findById(id);
        return foundOne.map(personne -> new ResponseEntity<>(personne, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Personne> deleteOnePerson(@PathVariable("id") String id){
        Optional<Personne> foundOne = repositories.findById(id);
        if(foundOne.isPresent()){
            repositories.delete(foundOne.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping
    public ResponseEntity<Personne> updatePerson(@RequestBody Personne p){
        repositories.save(p);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Personne> createPerson(@RequestBody Personne p){
        repositories.save(p);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }


}
