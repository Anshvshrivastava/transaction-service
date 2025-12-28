package com.finsight.transaction.service;

import com.finsight.transaction.entity.Transaction;
import com.finsight.transaction.entity.TransactionStatus;
import com.finsight.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import com.finsight.transaction.exception.DuplicateTransactionException;
import com.finsight.transaction.exception.TransactionNotFoundException;

@Service // Marks this class as a Spring-managed service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {

        if (transactionRepository.existsByReferenceNumber(
                transaction.getReferenceNumber())) {
            throw new DuplicateTransactionException(
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
                        new TransactionNotFoundException(
                                "Transaction not found with id: " + id
                        )
                );
    }

}
