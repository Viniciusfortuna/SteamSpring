package dev.matheuslf.security.service;

import dev.matheuslf.security.dto.request.CompraRequest;
import dev.matheuslf.security.dto.response.CompraResponse;
import dev.matheuslf.security.entity.Compra;
import dev.matheuslf.security.entity.Jogo;
import dev.matheuslf.security.entity.User;
import dev.matheuslf.security.repository.CompraRepository;
import dev.matheuslf.security.repository.JogoRepository;
import dev.matheuslf.security.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final UserRepository userRepository;
    private final JogoRepository jogoRepository;

    @Transactional
    public CompraResponse create(CompraRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Jogo jogo = jogoRepository.findById(request.jogoId())
                .orElseThrow(() -> new EntityNotFoundException("Jogo não encontrado"));
        
        if (request.valorPago().compareTo(jogo.getPreco()) < 0) {
            throw new IllegalArgumentException("Valor pago é menor que o preço do jogo.");
        }

        Compra compra = new Compra();
        compra.setUser(user);
        compra.setJogo(jogo);
        compra.setValorPago(request.valorPago());

        return toResponse(compraRepository.save(compra));
    }

    public List<CompraResponse> findAll() {
        return compraRepository.findAll().stream().map(this::toResponse).toList();
    }

    public CompraResponse findById(Long id) {
        return compraRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Compra não encontrada"));
    }

    private CompraResponse toResponse(Compra compra) {
        return new CompraResponse(
                compra.getId(),
                compra.getDataCompra(),
                compra.getUser().getId(),
                compra.getUser().getName(),
                compra.getJogo().getId(),
                compra.getJogo().getTitulo(),
                compra.getValorPago()
        );
    }
}
