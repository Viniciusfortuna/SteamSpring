package dev.matheuslf.security.service;

import dev.matheuslf.security.dto.request.PerfilRequest;
import dev.matheuslf.security.dto.response.PerfilResponse;
import dev.matheuslf.security.entity.Perfil;
import dev.matheuslf.security.entity.User;
import dev.matheuslf.security.repository.PerfilRepository;
import dev.matheuslf.security.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final UserRepository userRepository;

    @Transactional
    public PerfilResponse create(PerfilRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Perfil perfil = new Perfil();
        perfil.setNickname(request.nickname());
        perfil.setBio(request.bio());
        perfil.setNivel(request.nivel());
        perfil.setImagemPerfil(request.imagemPerfil());
        perfil.setUser(user);

        return toResponse(perfilRepository.save(perfil));
    }

    public List<PerfilResponse> findAll() {
        return perfilRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public PerfilResponse findById(Long id) {
        return perfilRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado"));
    }

    @Transactional
    public PerfilResponse update(Long id, PerfilRequest request) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado"));

        perfil.setNickname(request.nickname());
        perfil.setBio(request.bio());
        perfil.setNivel(request.nivel());
        perfil.setImagemPerfil(request.imagemPerfil());

        return toResponse(perfilRepository.save(perfil));
    }

    public void delete(Long id) {
        if (!perfilRepository.existsById(id))
            throw new EntityNotFoundException("Perfil não encontrado");
        perfilRepository.deleteById(id);
    }

    private PerfilResponse toResponse(Perfil perfil) {
        return new PerfilResponse(
                perfil.getId(),
                perfil.getNickname(),
                perfil.getBio(),
                perfil.getNivel(),
                perfil.getImagemPerfil(),
                perfil.getUser().getId(),
                perfil.getUser().getName()
        );
    }
}
