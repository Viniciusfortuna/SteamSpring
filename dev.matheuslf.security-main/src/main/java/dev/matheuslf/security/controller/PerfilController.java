package dev.matheuslf.security.controller;

import dev.matheuslf.security.dto.request.PerfilRequest;
import dev.matheuslf.security.dto.response.PerfilResponse;
import dev.matheuslf.security.service.PerfilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;

    @PostMapping
    public ResponseEntity<PerfilResponse> create(@Valid @RequestBody PerfilRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(perfilService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<PerfilResponse>> findAll() {
        return ResponseEntity.ok(perfilService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(perfilService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilResponse> update(@PathVariable Long id,
                                                 @Valid @RequestBody PerfilRequest request) {
        return ResponseEntity.ok(perfilService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        perfilService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
