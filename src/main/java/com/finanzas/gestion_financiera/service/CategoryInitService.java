package com.finanzas.gestion_financiera.service;

import com.finanzas.gestion_financiera.entity.Category;
import com.finanzas.gestion_financiera.entity.User;
import com.finanzas.gestion_financiera.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryInitService {

    private final CategoryRepository categoryRepository;

    public void crearCategoriasPorDefecto(User user) {
        List<Category> categorias = List.of(
                crearCategoria("Salario",         Category.TipoCategoria.INGRESO, new BigDecimal("0.00"), user),
                crearCategoria("Freelance",       Category.TipoCategoria.INGRESO, new BigDecimal("0.00"), user),
                crearCategoria("Inversiones",     Category.TipoCategoria.INGRESO, new BigDecimal("0.00"), user),
                crearCategoria("Otros ingresos",  Category.TipoCategoria.INGRESO, new BigDecimal("0.00"), user),
                crearCategoria("Alimentación",    Category.TipoCategoria.GASTO,   new BigDecimal("500.00"), user),
                crearCategoria("Transporte",      Category.TipoCategoria.GASTO,   new BigDecimal("300.00"), user),
                crearCategoria("Vivienda",        Category.TipoCategoria.GASTO,   new BigDecimal("1200.00"), user),
                crearCategoria("Salud",           Category.TipoCategoria.GASTO,   new BigDecimal("250.00"), user),
                crearCategoria("Entretenimiento", Category.TipoCategoria.GASTO,   new BigDecimal("200.00"), user),
                crearCategoria("Educación",       Category.TipoCategoria.GASTO,   new BigDecimal("400.00"), user)
        );
        categoryRepository.saveAll(categorias);
    }

    private Category crearCategoria(String nombre, Category.TipoCategoria tipo, BigDecimal montoMaximo, User user) {
        Category category = new Category();
        category.setNombre(nombre);
        category.setTipo(tipo);
        category.setMontoMaximo(montoMaximo);
        category.setUsuario(user);
        return category;
    }
}
