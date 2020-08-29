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
public class InsertSecondService {
    private final TransactionMapper transactionMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveSecondBankAccount() {
        transactionMapper.deleteById(19);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setFullName("Tom");
        bankAccount.setBalance(new BigDecimal("700"));
        transactionMapper.insert(bankAccount);
        //        if (new Random().nextBoolean()) {
        //            throw new RuntimeException("DummyException: this should cause rollback of both inserts!");
        //        }
    }
}
