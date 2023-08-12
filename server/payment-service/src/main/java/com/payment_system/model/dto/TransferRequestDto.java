package com.payment_system.model.dto;


import java.math.BigDecimal;
public record TransferRequestDto(
        String from,
        String to,
        BigDecimal amount
) {}
