package com.finanzas.gestion_financiera.controller;

import com.finanzas.gestion_financiera.dto.TransactionRequest;
import com.finanzas.gestion_financiera.dto.TransactionResponse;
import com.finanzas.gestion_financiera.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transacciones")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> crear(
            @Valid @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> listar() {
        return ResponseEntity.ok(transactionService.listar());
    }
}