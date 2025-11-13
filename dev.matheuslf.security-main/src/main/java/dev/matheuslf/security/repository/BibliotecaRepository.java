package dev.matheuslf.security.repository;

import dev.matheuslf.security.entity.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {
}
