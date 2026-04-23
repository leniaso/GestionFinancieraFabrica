package com.finanzas.gestion_financiera.dto;

import com.finanzas.gestion_financiera.entity.Category.TipoCategoria;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CategoryRequest {

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String nombre;

    @NotNull(message = "El tipo es obligatorio, debe ser INGRESO o GASTO")
    private TipoCategoria tipo;

    @NotNull(message = "El monto máximo es obligatorio")
    @DecimalMin(value = "1.00", message = "El monto máximo debe ser mayor a 0")
    private BigDecimal montoMaximo;
}
