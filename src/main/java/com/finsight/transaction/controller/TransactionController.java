package com.finsight.transaction.controller;

import com.finsight.transaction.dto.CreateTransactionRequest;
import com.finsight.transaction.entity.Transaction;
import com.finsight.transaction.entity.TransactionType;
import com.finsight.transaction.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController // Marks this as REST API controller
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // POST /api/transactions
    @PostMapping
    public Transaction createTransaction(
            @Valid @RequestBody CreateTransactionRequest request
    ) {

        Transaction transaction = Transaction.builder()
                .referenceNumber(request.getReferenceNumber())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .type(TransactionType.valueOf(request.getType()))
                .sourceSystem(request.getSourceSystem())
                .build();

        return transactionService.createTransaction(transaction);
    }

    // GET /api/transactions/{id}
    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }
}
