package dev.matheuslf.security.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PerfilRequest(
        @NotEmpty(message = "Nickname é obrigatório") String nickname,
        @Size(max = 255, message = "Bio deve ter no máximo 255 caracteres") String bio,
        int nivel,
        String imagemPerfil,
        Long userId
) {}
