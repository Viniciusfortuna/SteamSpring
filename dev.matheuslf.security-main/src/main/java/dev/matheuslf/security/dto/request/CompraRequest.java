package dev.matheuslf.security.dto.request;

import java.math.BigDecimal;

public record CompraRequest(
        Long userId,
        Long jogoId,
        BigDecimal valorPago
) {}
