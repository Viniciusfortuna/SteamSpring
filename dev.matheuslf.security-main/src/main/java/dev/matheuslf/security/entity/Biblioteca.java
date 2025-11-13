package dev.matheuslf.security.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "bibliotecas")
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataCriacao = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "biblioteca_jogo",
            joinColumns = @JoinColumn(name = "biblioteca_id"),
            inverseJoinColumns = @JoinColumn(name = "jogo_id")
    )
    private Set<Jogo> jogos = new HashSet<>();
}
