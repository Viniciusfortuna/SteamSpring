package dev.matheuslf.security.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "perfis")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String bio;
    private int nivel;
    private String imagemPerfil;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
