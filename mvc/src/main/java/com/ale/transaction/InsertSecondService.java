package com.ale.transaction;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class InsertSecondService {
    private final TransactionMapper transactionMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveSecondBankAccount() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setFullName("Tom");
        bankAccount.setBalance(new BigDecimal("700"));
        transactionMapper.insert(bankAccount);
        if (new Random().nextBoolean()) {
            throw new RuntimeException("DummyException: this should cause rollback of both inserts!");
        }
    }

    public void update() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(3);
        bankAccount.setFullName("Donald");
        bankAccount.setBalance(new BigDecimal("1700"));
        transactionMapper.updateById(bankAccount);
        if (new Random().nextBoolean()) {
            throw new RuntimeException("DummyException: this should cause rollback of both update!");
        }
    }
}
