package pe.com.project1.ms.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.project1.ms.application.FindBankAccountUseCase;
import pe.com.project1.ms.application.persistence.BankAccountRepository;
import pe.com.project1.ms.domain.bank.account.BankAccount;
import pe.com.project1.ms.domain.bank.account.BankAccountType;
import pe.com.project1.ms.domain.bank.transaction.BankingTransaction;
import pe.com.project1.ms.domain.bank.transaction.BankingTransactionType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FindBankAccountService implements FindBankAccountUseCase {

    private BankAccountRepository bankAccountRepository;

    @Override
    public Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber) {
        return bankAccountRepository.findByBankAccountNumber(bankAccountNumber);
    }

    @Override
    public Flux<BankAccount> findByAccountHolderId(String accountHolderId) {
        return bankAccountRepository.findByAccountHolderId(accountHolderId);
    }

    @Override
    public Flux<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public Flux<BankAccount> findByBankAccountType(BankAccountType bankAccountType) {
        return bankAccountRepository.findByBankAccountType(bankAccountType);
    }

    @Override
    public Mono<BankAccount> updateBalance(BankAccount bankAccount, BankingTransaction bankingTransaction) {
        BankingTransactionType bankingTransactionType = bankingTransaction.getBankingTransactionType();
        BigDecimal amount = bankingTransaction.getAmount();
        if (bankingTransactionType.equals(BankingTransactionType.WITHDRAWAL)) {
            bankAccount.withdraw(amount);
        } else if (bankingTransactionType.equals(BankingTransactionType.DEPOSIT)) {
            bankAccount.deposit(amount);
        } else {
            return Mono.error(new RuntimeException("Transaccion no valida"));
        }
        return bankAccountRepository.save(bankAccount);
    }

}
