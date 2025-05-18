package com.homework.transactionmanager.repository;

import com.homework.transactionmanager.entity.Transaction;
import com.homework.transactionmanager.enums.TransactionType;
import com.homework.transactionmanager.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void testSave() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEPOSIT.name());
        transaction.setAmount(new BigDecimal("10.00"));
        transaction.setDate(LocalDateTime.now());
        transaction.setDescription("test111");
        Transaction saved = transactionRepository.save(transaction);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(TransactionType.DEPOSIT.name(), saved.getType());
        assertEquals(new BigDecimal("10.00"), saved.getAmount());
        assertEquals("test111", saved.getDescription());
        assertEquals(transaction.getDate(), saved.getDate());
    }

    @Test
    void testGetById() {
        Transaction transaction = transactionRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("transaction not exist，ID: " + 1L));;
        assertEquals(transaction.getId(), 1L);
    }

    @Test
    void testUpdate() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setType(TransactionType.DEPOSIT.name());
        transaction.setAmount(new BigDecimal("10.00"));
        transaction.setDate(LocalDateTime.now());
        transaction.setDescription("test111");
        Transaction saved = transactionRepository.save(transaction);
        assertEquals(saved.getId(), 1L);
        assertEquals(TransactionType.DEPOSIT.name(), saved.getType());
        assertEquals(new BigDecimal("10.00"), saved.getAmount());
        assertEquals("test111", saved.getDescription());
        assertEquals(transaction.getDate(), saved.getDate());
    }

    @Test
    void testDelete() {
        transactionRepository.deleteById(1L);
    }
}
