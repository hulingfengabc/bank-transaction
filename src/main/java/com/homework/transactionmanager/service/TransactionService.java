package com.homework.transactionmanager.service;

import com.homework.transactionmanager.exception.DuplicateTransactionException;
import com.homework.transactionmanager.exception.TransactionNotFoundException;
import com.homework.transactionmanager.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction) throws DuplicateTransactionException;

    Page<Transaction> getAllTransactions(Pageable pageable);

    Transaction getTransactionById(Long id) throws TransactionNotFoundException;

    Transaction updateTransaction(Long id, Transaction transactionDetails) throws TransactionNotFoundException, DuplicateTransactionException;

    void deleteTransaction(Long id) throws TransactionNotFoundException;
}