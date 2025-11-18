package dev.matheuslf.security.controller;

import dev.matheuslf.security.dto.request.CompraRequest;
import dev.matheuslf.security.dto.response.CompraResponse;
import dev.matheuslf.security.service.CompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<CompraResponse> create(@Valid @RequestBody CompraRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(compraService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<CompraResponse>> findAll() {
        return ResponseEntity.ok(compraService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(compraService.findById(id));
    }
}
