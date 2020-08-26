package com.ale.transaction;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author alewu
 * @date 2020/8/25
 */
@Service
@Slf4j
@AllArgsConstructor
public class TransactionServiceImpl {
    private final TransactionMapper transactionMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = BankTransactionException.class)
    public void sendMoney(Long fromAccountId, Long toAccountId, BigDecimal amount) throws BankTransactionException {
        addAmount(toAccountId, amount);
        addAmount(fromAccountId, amount.negate());
    }


    public void addAmount(Long id, BigDecimal amount) throws BankTransactionException {
        BankAccount account = transactionMapper.selectById(id);
        if (account == null) {
            throw new BankTransactionException("Account not found " + id);
        }
        BigDecimal newBalance = amount.add(account.getBalance());
        if (amount.add(account.getBalance()).compareTo(BigDecimal.ZERO) == -1) {
            throw new BankTransactionException(
                    "The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
        transactionMapper.updateById(account);
    }
}
