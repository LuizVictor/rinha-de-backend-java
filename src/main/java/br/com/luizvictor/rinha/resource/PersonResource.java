package br.com.luizvictor.rinha.resource;

import br.com.luizvictor.rinha.entity.Person;
import br.com.luizvictor.rinha.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class PersonResource {
    private final PersonRepository repository;
    private final Logger log = LoggerFactory.getLogger(PersonResource.class);

    public PersonResource(PersonRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/pessoas")
    @Transactional
    public ResponseEntity<Person> save(@RequestBody Person person) {
        var saved = new Person(
                UUID.randomUUID(),
                person.getNome(),
                person.getApelido(),
                person.getNascimento(),
                person.getStack()
        );

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
        if (term.isEmpty() || term.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        log.info("Searching for: " + term);
        var people = repository.findAllByTerm(term);
        return ResponseEntity.ok(people);
    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<String> count() {
        String count = String.valueOf(repository.count());
        return ResponseEntity.ok().body(count);
    }
}