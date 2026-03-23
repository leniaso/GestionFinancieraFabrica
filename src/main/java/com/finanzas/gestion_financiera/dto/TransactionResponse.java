package com.finanzas.gestion_financiera.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TransactionResponse {
    private Long id;
    private String tipo;
    private BigDecimal monto;
    private LocalDate fecha;
    private String categoria;
}