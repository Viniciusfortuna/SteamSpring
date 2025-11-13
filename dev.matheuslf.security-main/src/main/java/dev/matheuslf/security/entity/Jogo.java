package dev.matheuslf.security.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "jogos")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String categoria;
    private BigDecimal preco;
    private LocalDate dataLancamento;

    @ManyToMany(mappedBy = "jogos")
    private Set<Biblioteca> bibliotecas = new HashSet<>();

    @ManyToMany(mappedBy = "jogos")
    private Set<Inventario> inventarios = new HashSet<>();
}
