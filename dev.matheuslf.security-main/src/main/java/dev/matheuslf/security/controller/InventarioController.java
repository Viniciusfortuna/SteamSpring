package dev.matheuslf.security.controller;

import dev.matheuslf.security.dto.request.InventarioRequest;
import dev.matheuslf.security.dto.response.InventarioResponse;
import dev.matheuslf.security.service.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<InventarioResponse> create(@Valid @RequestBody InventarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<InventarioResponse>> findAll() {
        return ResponseEntity.ok(inventarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponse> update(@PathVariable Long id,
                                                     @Valid @RequestBody InventarioRequest request) {
        return ResponseEntity.ok(inventarioService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
