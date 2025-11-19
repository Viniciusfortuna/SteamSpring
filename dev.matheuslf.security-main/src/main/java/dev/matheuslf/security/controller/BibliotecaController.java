package dev.matheuslf.security.controller;

import dev.matheuslf.security.dto.request.BibliotecaRequest;
import dev.matheuslf.security.dto.response.BibliotecaResponse;
import dev.matheuslf.security.service.BibliotecaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblioteca")
@RequiredArgsConstructor
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    @PostMapping
    public ResponseEntity<BibliotecaResponse> create(@Valid @RequestBody BibliotecaRequest request) {
        BibliotecaResponse response = bibliotecaService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BibliotecaResponse>> findAll() {
        return ResponseEntity.ok(bibliotecaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BibliotecaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bibliotecaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BibliotecaResponse> update(@PathVariable Long id,
                                                     @Valid @RequestBody BibliotecaRequest request) {
        return ResponseEntity.ok(bibliotecaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bibliotecaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
