package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class TransferRequestDto {
    private String from;
    private String to;
    private BigDecimal amount;
}
