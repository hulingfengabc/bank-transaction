package com.homework.transactionmanager.controller;

import com.homework.transactionmanager.entity.Transaction;
import com.homework.transactionmanager.enums.TransactionType;
import com.homework.transactionmanager.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    private Transaction testTransaction;

    @BeforeEach
    void setUp() {
        testTransaction = new Transaction();
        testTransaction.setId(1L);
        testTransaction.setType(TransactionType.DEPOSIT.name());
        testTransaction.setAmount(new BigDecimal("10.00"));
        testTransaction.setDate(LocalDateTime.now());
        testTransaction.setDescription("test111");
    }

    @Test
    void createTransaction_ReturnCreatedTransaction() throws Exception {
        String requestBody = "{\"type\": \"DEPOSIT\", \"amount\": 10, \"date\": \"2025-05-19 12:00\",\"description\": \"test1\"}";

        when(transactionService.createTransaction(any(Transaction.class))).thenReturn(testTransaction);
        mockMvc.perform(
                post("/transactions/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody)
                )
                .andExpect(status().isOk());
    }

    @Test
    void updateTransaction_ReturnOk() throws Exception {
        String requestBody = "{\"type\": \"DEPOSIT\", \"amount\": 10, \"date\": \"2025-05-19 12:00\",\"description\": \"test1\"}";

        when(transactionService.updateTransaction(eq(1L), any(Transaction.class)))
                .thenReturn(testTransaction);

        mockMvc.perform(
                post("/transactions/edit/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody)
                ).andExpect(status().isOk());
    }

    @Test
    void deleteTransaction_ShouldReturnOK() throws Exception {
        doNothing().when(transactionService).deleteTransaction(1L);
        mockMvc.perform(post("/transactions/delete/1"))
                .andExpect(status().is3xxRedirection());

    }
}
