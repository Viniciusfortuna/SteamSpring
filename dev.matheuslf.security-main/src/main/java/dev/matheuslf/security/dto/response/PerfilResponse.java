package dev.matheuslf.security.dto.response;

public record PerfilResponse(
        Long id,
        String nickname,
        String bio,
        int nivel,
        String imagemPerfil,
        Long userId,
        String userName
) {}
