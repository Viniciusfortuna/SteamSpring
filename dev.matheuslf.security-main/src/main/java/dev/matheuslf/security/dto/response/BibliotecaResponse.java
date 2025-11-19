package dev.matheuslf.security.dto.response;

import java.time.LocalDate;
import java.util.Set;

public record BibliotecaResponse(
        Long id,
        LocalDate dataCriacao,
        Long userId,
        String userName,
        Set<JogoResponse> jogos
) {}
