package dev.matheuslf.security.service;

import dev.matheuslf.security.dto.request.JogoRequest;
import dev.matheuslf.security.dto.response.JogoResponse;
import dev.matheuslf.security.entity.Jogo;
import dev.matheuslf.security.repository.JogoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogoService {

    private final JogoRepository jogoRepository;

    @Transactional
    public JogoResponse create(JogoRequest request) {
        Jogo jogo = new Jogo();
        jogo.setTitulo(request.titulo());
        jogo.setDescricao(request.descricao());
        jogo.setCategoria(request.categoria());
        jogo.setPreco(request.preco());
        jogo.setDataLancamento(request.dataLancamento());
        return toResponse(jogoRepository.save(jogo));
    }

    public List<JogoResponse> findAll() {
        return jogoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public JogoResponse findById(Long id) {
        return jogoRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Jogo não encontrado"));
    }

    @Transactional
    public JogoResponse update(Long id, JogoRequest request) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jogo não encontrado"));

        jogo.setTitulo(request.titulo());
        jogo.setDescricao(request.descricao());
        jogo.setCategoria(request.categoria());
        jogo.setPreco(request.preco());
        jogo.setDataLancamento(request.dataLancamento());

        return toResponse(jogoRepository.save(jogo));
    }

    public void delete(Long id) {
        if (!jogoRepository.existsById(id))
            throw new EntityNotFoundException("Jogo não encontrado");
        jogoRepository.deleteById(id);
    }

    private JogoResponse toResponse(Jogo jogo) {
        return new JogoResponse(
                jogo.getId(),
                jogo.getTitulo(),
                jogo.getDescricao(),
                jogo.getCategoria(),
                jogo.getPreco(),
                jogo.getDataLancamento()
        );
    }
}
