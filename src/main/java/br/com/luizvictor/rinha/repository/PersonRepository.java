package br.com.luizvictor.rinha.repository;

import br.com.luizvictor.rinha.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    @Query(nativeQuery = true, value = "SELECT p.* FROM people p WHERE searchable ILIKE %:term%")
    List<Person> findAllByTerm(@Param("term") String term);
}
