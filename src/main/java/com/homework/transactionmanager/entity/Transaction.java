package com.homework.transactionmanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The transaction type cannot be empty.")
    @Size(max = 20, message = "The transaction type cannot exceed 20 characters.")
    private String type;

    @NotNull(message = "The amount cannot be empty.")
    @Positive(message = "The amount must be a positive number.")
    private BigDecimal amount;

    private LocalDateTime date = LocalDateTime.now();

    @Size(max = 255, message = "描述不能超过255个字符")
    private String description;

    public boolean isDuplicate(Transaction other) {
        return this.type.equals(other.type)
                && this.amount.equals(other.amount)
                && this.date.equals(other.date)
                && this.description.equals(other.description);
    }
}