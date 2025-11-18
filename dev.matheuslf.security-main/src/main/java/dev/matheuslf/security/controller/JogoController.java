package dev.matheuslf.security.controller;

import dev.matheuslf.security.dto.request.JogoRequest;
import dev.matheuslf.security.dto.response.JogoResponse;
import dev.matheuslf.security.service.JogoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
@RequiredArgsConstructor
public class JogoController {

    private final JogoService jogoService;

    @PostMapping
    public ResponseEntity<JogoResponse> create(@Valid @RequestBody JogoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jogoService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<JogoResponse>> findAll() {
        return ResponseEntity.ok(jogoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(jogoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogoResponse> update(@PathVariable Long id,
                                               @Valid @RequestBody JogoRequest request) {
        return ResponseEntity.ok(jogoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jogoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
