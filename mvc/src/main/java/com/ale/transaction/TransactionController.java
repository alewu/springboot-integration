package com.ale.transaction;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author alewu
 * @date 2020/8/25
 */
@RestController
@RequestMapping("/")
@Slf4j
@AllArgsConstructor
public class TransactionController {
    private final TransactionServiceImpl transactionService;
    private final InsertFirstService insertFirstService;

    /**
     * @author alewu
     * @date 2020/8/25 13:46
     */
    @PostMapping("/transaction/sendMoney")
    public ResponseEntity<String> get(@RequestBody SendMoneyForm form) throws BankTransactionException {
        transactionService.sendMoney(form.getFromAccountId(), form.getToAccountId(), form.getAmount());
        return ResponseEntity.ok("");
    }

    /**
     * @author alewu
     * @date 2020/8/25 16:00
     */
    @GetMapping("/propagation")
    public ResponseEntity<String> propagation() {
        insertFirstService.saveBankAccount();
        return ResponseEntity.ok("");
    }
}
