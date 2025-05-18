package com.homework.transactionmanager.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class TransactionMetrics {

    private final Counter transactionCounter;

    private final Counter queryTransactionCounter;

    private final MeterRegistry meterRegistry;

    public TransactionMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.transactionCounter = Counter.builder("transactions.total")
                .description("Total number of transactions created")
                .register(meterRegistry);
        this.queryTransactionCounter = Counter.builder("query.transactions.total")
                .description("Total number of transactions query")
                .register(meterRegistry);
    }

    public void incrementTransactionCounter() {
        transactionCounter.increment();
    }

    public void invrementQueryTransactionCounter() {
        queryTransactionCounter.increment();
    }
}
