package com.finanzas.gestion_financiera.service;

import com.finanzas.gestion_financiera.dto.TransactionRequest;
import com.finanzas.gestion_financiera.dto.TransactionResponse;
import com.finanzas.gestion_financiera.entity.Category;
import com.finanzas.gestion_financiera.entity.Transaction;
import com.finanzas.gestion_financiera.entity.User;
import com.finanzas.gestion_financiera.repository.CategoryRepository;
import com.finanzas.gestion_financiera.repository.TransactionRepository;
import com.finanzas.gestion_financiera.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public TransactionResponse crear(TransactionRequest request) {
        // Obtener el usuario autenticado desde el token
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar que la categoría existe
        Category category = categoryRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no válida"));

        // Crear la transacción
        Transaction transaction = new Transaction();
        transaction.setTipo(request.getTipo());
        transaction.setMonto(request.getMonto());
        transaction.setFecha(request.getFecha());
        transaction.setUsuario(user);
        transaction.setCategoria(category);

        transactionRepository.save(transaction);

        return new TransactionResponse(
                transaction.getId(),
                transaction.getTipo(),
                transaction.getMonto(),
                transaction.getFecha(),
                category.getNombre()
        );
    }

    public List<TransactionResponse> listar() {
        // Obtener el usuario autenticado desde el token
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return transactionRepository.findByUsuarioId(user.getId())
                .stream()
                .map(t -> new TransactionResponse(
                        t.getId(),
                        t.getTipo(),
                        t.getMonto(),
                        t.getFecha(),
                        t.getCategoria().getNombre()
                ))
                .collect(Collectors.toList());
    }
}