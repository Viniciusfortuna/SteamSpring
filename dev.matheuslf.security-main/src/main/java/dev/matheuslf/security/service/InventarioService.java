package dev.matheuslf.security.service;

import dev.matheuslf.security.dto.request.InventarioRequest;
import dev.matheuslf.security.dto.response.InventarioResponse;
import dev.matheuslf.security.dto.response.JogoResponse;
import dev.matheuslf.security.entity.Inventario;
import dev.matheuslf.security.entity.Jogo;
import dev.matheuslf.security.entity.User;
import dev.matheuslf.security.repository.InventarioRepository;
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
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final UserRepository userRepository;
    private final JogoRepository jogoRepository;

    @Transactional
    public InventarioResponse create(InventarioRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Inventario inventario = new Inventario();
        inventario.setNomeItem(request.nomeItem());
        inventario.setRaridade(request.raridade());
        inventario.setTipoItem(request.tipoItem());
        inventario.setUser(user);

        if (request.jogosIds() != null) {
            Set<Jogo> jogos = jogoRepository.findAllById(request.jogosIds()).stream().collect(Collectors.toSet());
            inventario.setJogos(jogos);
        }

        return toResponse(inventarioRepository.save(inventario));
    }

    public List<InventarioResponse> findAll() {
        return inventarioRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public InventarioResponse findById(Long id) {
        return inventarioRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Inventário não encontrado"));
    }

    @Transactional
    public InventarioResponse update(Long id, InventarioRequest request) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventário não encontrado"));

        inventario.setNomeItem(request.nomeItem());
        inventario.setRaridade(request.raridade());
        inventario.setTipoItem(request.tipoItem());

        if (request.jogosIds() != null) {
            Set<Jogo> jogos = jogoRepository.findAllById(request.jogosIds()).stream().collect(Collectors.toSet());
            inventario.setJogos(jogos);
        }

        return toResponse(inventarioRepository.save(inventario));
    }

    public void delete(Long id) {
        if (!inventarioRepository.existsById(id))
            throw new EntityNotFoundException("Inventário não encontrado");
        inventarioRepository.deleteById(id);
    }

    private InventarioResponse toResponse(Inventario inventario) {
        Set<JogoResponse> jogos = inventario.getJogos().stream()
                .map(j -> new JogoResponse(j.getId(), j.getTitulo(), j.getDescricao(), j.getCategoria(), j.getPreco(), j.getDataLancamento()))
                .collect(Collectors.toSet());

        return new InventarioResponse(
                inventario.getId(),
                inventario.getNomeItem(),
                inventario.getRaridade(),
                inventario.getTipoItem(),
                inventario.getUser().getId(),
                inventario.getUser().getName(),
                jogos
        );
    }
}
