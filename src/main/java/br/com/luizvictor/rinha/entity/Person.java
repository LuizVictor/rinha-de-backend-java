package br.com.luizvictor.rinha.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "people")
public class Person {
    @Id
    private UUID id;
    @Column(unique = true, length = 32)
    @NotNull
    private String nome;
    @NotNull
    @Column(unique = true, length = 100)
    private String apelido;
    private String nascimento;
    @ElementCollection
    @Column(name = "stack")
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
}
