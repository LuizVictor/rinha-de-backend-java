package br.com.luizvictor.rinha.resource;

import br.com.luizvictor.rinha.entity.Person;
import br.com.luizvictor.rinha.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class PersonResource {
    private final PersonRepository repository;

    public PersonResource(PersonRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/pessoas")
    public ResponseEntity<Person> save(@RequestBody Person person) {
        var saved = new Person(UUID.randomUUID(), person.getNome(), person.getApelido(), person.getNascimento(), person.getStack());
        repository.save(saved);
        return ResponseEntity.created(URI.create("/pessoas/" + saved.getId())).body(saved);
    }

    @GetMapping("pessoas/{id}")
    public ResponseEntity<Person> findById(@PathVariable UUID id) {
        var person = repository.findById(id).orElse(null);

        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(person);
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<Person>> findAll(@RequestParam(name = "t") String term) {
        var people = repository.findAllByTerm(term);
        return ResponseEntity.ok(people);
    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<String> count() {
        String count = Long.toString(repository.count());
        return ResponseEntity.ok().body(count);
    }
}
