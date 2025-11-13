package dev.matheuslf.security.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record JogoRequest(
        @NotEmpty(message = "Título é obrigatório") String titulo,
        @NotEmpty(message = "Descrição é obrigatória") String descricao,
        @NotEmpty(message = "Categoria é obrigatória") String categoria,
        @NotNull(message = "Preço é obrigatório") BigDecimal preco,
        @NotNull(message = "Data de lançamento é obrigatória") LocalDate dataLancamento
) {}
