package com.finanzas.gestion_financiera.controller;

import com.finanzas.gestion_financiera.dto.CategoryRequest;
import com.finanzas.gestion_financiera.dto.CategoryResponse;
import com.finanzas.gestion_financiera.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> crear(@Valid @RequestBody CategoryRequest request) {
        CategoryResponse response = categoryService.crear(request);
        agregarLinks(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<CategoryResponse>> listar() {
        List<CategoryResponse> lista = categoryService.listar();
        lista.forEach(this::agregarLinks);

        CollectionModel<CategoryResponse> collection = CollectionModel.of(
                lista,
                linkTo(methodOn(CategoryController.class).listar()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> obtener(@PathVariable Long id) {
        CategoryResponse response = categoryService.obtener(id);
        agregarLinks(response);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request) {
        CategoryResponse response = categoryService.actualizar(id, request);
        agregarLinks(response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoryService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ─── Links reutilizables ───────────────────────────────────────────────
    private void agregarLinks(CategoryResponse response) {
        Long id = response.getId();
        response.add(
                linkTo(methodOn(CategoryController.class).obtener(id)).withSelfRel(),
                linkTo(methodOn(CategoryController.class).actualizar(id, null)).withRel("actualizar"),
                linkTo(methodOn(CategoryController.class).eliminar(id)).withRel("eliminar"),
                linkTo(methodOn(CategoryController.class).listar()).withRel("todas")
        );
    }
}