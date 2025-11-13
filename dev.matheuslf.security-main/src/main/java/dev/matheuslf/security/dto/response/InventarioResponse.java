package dev.matheuslf.security.dto.response;

import java.util.Set;

public record InventarioResponse(
        Long id,
        String nomeItem,
        String raridade,
        String tipoItem,
        Long userId,
        String userName,
        Set<JogoResponse> jogos
) {}
