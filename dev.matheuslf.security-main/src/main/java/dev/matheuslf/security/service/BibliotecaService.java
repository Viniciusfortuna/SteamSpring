package dev.matheuslf.security.service;

import dev.matheuslf.security.dto.request.BibliotecaRequest;
import dev.matheuslf.security.dto.response.BibliotecaResponse;
import dev.matheuslf.security.dto.response.JogoResponse;
import dev.matheuslf.security.entity.Biblioteca;
import dev.matheuslf.security.entity.Jogo;
import dev.matheuslf.security.entity.User;
import dev.matheuslf.security.repository.BibliotecaRepository;
import dev.matheuslf.security.repository.JogoRepository;
import dev.matheuslf.security.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BibliotecaService {

    private final BibliotecaRepository bibliotecaRepository;
    private final UserRepository userRepository;
    private final JogoRepository jogoRepository;

    @Transactional
    public BibliotecaResponse create(BibliotecaRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setUser(user);

        if (request.jogosIds() != null) {
            Set<Jogo> jogos = jogoRepository.findAllById(request.jogosIds()).stream().collect(Collectors.toSet());
            biblioteca.setJogos(jogos);
        }

        return toResponse(bibliotecaRepository.save(biblioteca));
    }

    public List<BibliotecaResponse> findAll() {
        return bibliotecaRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public BibliotecaResponse findById(Long id) {
        return bibliotecaRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Biblioteca não encontrada"));
    }

    @Transactional
    public BibliotecaResponse update(Long id, BibliotecaRequest request) {
        Biblioteca biblioteca = bibliotecaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Biblioteca não encontrada"));

        if (request.jogosIds() != null) {
            Set<Jogo> jogos = jogoRepository.findAllById(request.jogosIds()).stream().collect(Collectors.toSet());
            biblioteca.setJogos(jogos);
        }

        return toResponse(bibliotecaRepository.save(biblioteca));
    }

    public void delete(Long id) {
        if (!bibliotecaRepository.existsById(id))
            throw new EntityNotFoundException("Biblioteca não encontrada");
        bibliotecaRepository.deleteById(id);
    }

    private BibliotecaResponse toResponse(Biblioteca biblioteca) {
        Set<JogoResponse> jogos = biblioteca.getJogos().stream()
                .map(j -> new JogoResponse(j.getId(), j.getTitulo(), j.getDescricao(), j.getCategoria(), j.getPreco(), j.getDataLancamento()))
                .collect(Collectors.toSet());

        return new BibliotecaResponse(
                biblioteca.getId(),
                biblioteca.getDataCriacao(),
                biblioteca.getUser().getId(),
                biblioteca.getUser().getName(),
                jogos
        );
    }
}
