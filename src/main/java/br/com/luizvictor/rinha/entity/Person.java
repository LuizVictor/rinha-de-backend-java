package br.com.luizvictor.rinha.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "people")
public class Person implements Serializable {
    @Id
    private UUID id;
    @Column(length = 32, nullable = false)
    private String nome;
    @Column(unique = true, length = 100, nullable = false)
    private String apelido;
    @Column(nullable = false)
    private String nascimento;
    @Convert(converter = StringConverter.class)
    private List<String> stack;

    public Person(UUID id, String nome, String apelido, String nascimento, List<String> stack) {
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.nascimento = nascimento;
        this.stack = stack;
    }

    public Person() {
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }

    public String getNascimento() {
        return nascimento;
    }

    public List<String> getStack() {
        return stack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
