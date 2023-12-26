package br.com.luizvictor.rinha.repository;

import br.com.luizvictor.rinha.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    @Query(nativeQuery = true, value = "SELECT p.* FROM people p WHERE p.nome LIKE CONCAT('%', :term, '%') OR p.apelido LIKE CONCAT('%', :term, '%') OR p.stack LIKE CONCAT('%', :term ,'%') ")
    List<Person> findAllByTerm(String term);
}
