package com.banking_app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithDrawRequest {
    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be greater than zero")
    @Min(value = 1, message = "Amount must be at least 1")
    private Long amount;
}
