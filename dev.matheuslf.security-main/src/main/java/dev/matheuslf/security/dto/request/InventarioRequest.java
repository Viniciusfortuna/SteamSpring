package dev.matheuslf.security.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record InventarioRequest(
        @NotEmpty(message = "Nome do item é obrigatório") String nomeItem,
        @NotEmpty(message = "Raridade é obrigatória") String raridade,
        @NotEmpty(message = "Tipo do item é obrigatório") String tipoItem,
        @NotNull(message = "Usuário é obrigatório") Long userId,
        Set<Long> jogosIds
) {}
