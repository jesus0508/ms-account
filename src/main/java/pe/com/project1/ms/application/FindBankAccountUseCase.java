package pe.com.project1.ms.application;

import pe.com.project1.ms.domain.bank.account.BankAccount;
import pe.com.project1.ms.domain.bank.account.BankAccountType;
import pe.com.project1.ms.domain.bank.transaction.BankingTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindBankAccountUseCase {

    Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber);

    Flux<BankAccount> findByAccountHolderId(String accountHolderId);

    Flux<BankAccount> findAll();

    Flux<BankAccount> findByBankAccountType(BankAccountType bankAccountType);

    Mono<BankAccount> updateBalance(BankAccount bankAccount, BankingTransaction bankingTransaction);

}
