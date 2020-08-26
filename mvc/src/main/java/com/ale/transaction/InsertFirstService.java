package com.ale.transaction;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
@AllArgsConstructor
public class InsertFirstService {
    private final TransactionMapper transactionMapper;
    private final InsertSecondService insertSecondService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveBankAccount() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setFullName("jack");
        bankAccount.setBalance(new BigDecimal("3.25"));
        transactionMapper.insert(bankAccount);

        insertSecondService.saveSecondBankAccount();
    }
}
