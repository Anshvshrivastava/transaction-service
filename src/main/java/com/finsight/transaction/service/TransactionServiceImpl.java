package com.finsight.transaction.service;

import com.finsight.transaction.entity.Transaction;
import com.finsight.transaction.entity.TransactionStatus;
import com.finsight.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;
@Service // Marks this class as a Spring-managed service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {

        boolean exists = transactionRepository.existsByReferenceNumber(transaction.getReferenceNumber());
        if (exists) {
            throw new RuntimeException(
                    "Transaction with reference number already exists"
            );
        }
        transaction.setStatus(TransactionStatus.RECEIVED);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Transaction not found")
                );
    }
}
