package dev.matheuslf.security.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record JogoResponse(
        Long id,
        String titulo,
        String descricao,
        String categoria,
        BigDecimal preco,
        LocalDate dataLancamento
) {}
