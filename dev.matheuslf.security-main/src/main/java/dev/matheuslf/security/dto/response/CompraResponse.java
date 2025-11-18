package dev.matheuslf.security.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CompraResponse(
        Long id,
        LocalDateTime dataCompra,
        Long userId,
        String userName,
        Long jogoId,
        String jogoTitulo,
        BigDecimal valorPago
) {}
