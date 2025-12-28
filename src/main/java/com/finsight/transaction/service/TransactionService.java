package com.finsight.transaction.service;

import com.finsight.transaction.entity.Transaction;

public interface TransactionService {

    Transaction createTransaction(Transaction transaction);

    Transaction getTransactionById(Long id);
}
