package com.ale.transaction.bean;

import lombok.Data;

import java.math.BigDecimal;
/**
  *
  * @author alewu
  * @date 2020/12/6
  */
@Data
public class SendMoneyForm {
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
}
