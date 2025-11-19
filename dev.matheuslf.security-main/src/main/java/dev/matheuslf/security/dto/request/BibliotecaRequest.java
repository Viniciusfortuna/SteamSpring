package dev.matheuslf.security.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record BibliotecaRequest(
        @NotNull(message = "Usuário é obrigatório") Long userId,
        Set<Long> jogosIds
) {}
