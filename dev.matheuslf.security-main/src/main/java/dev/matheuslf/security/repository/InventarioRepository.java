package dev.matheuslf.security.repository;

import dev.matheuslf.security.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}
