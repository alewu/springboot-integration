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
        //        insert();
        update();
    }

    private void update() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(2);
        bankAccount.setBalance(new BigDecimal("70"));
        transactionMapper.updateById(bankAccount);
        insertSecondService.update();
        //        if (new Random().nextBoolean()) {
        //            throw new RuntimeException("DummyException: this should cause rollback of both update!");
        //        }

    }

    private void insert() {
        try {
            insertSecondService.saveSecondBankAccount();
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        }
    }
}
