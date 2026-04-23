package com.finanzas.gestion_financiera.dto;

import com.finanzas.gestion_financiera.entity.Category.TipoCategoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CategoryResponse extends RepresentationModel<CategoryResponse>{

    private Long id;
    private String nombre;
    private TipoCategoria tipo;
    private BigDecimal montoMaximo;
}
