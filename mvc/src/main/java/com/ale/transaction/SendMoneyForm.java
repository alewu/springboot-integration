package com.ale.transaction;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SendMoneyForm {
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
}
