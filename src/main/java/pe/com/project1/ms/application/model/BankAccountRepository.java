package pe.com.project1.ms.application.model;

import pe.com.project1.ms.domain.bank.account.BankAccount;
import pe.com.project1.ms.domain.bank.account.BankAccountState;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountRepository {
	Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber);

	Mono<BankAccount> save(BankAccount bankAccount);

	Mono<BankAccount> update(BankAccount bankAccount, String bankAccountNumber);

	Flux<BankAccount> findByAccountHolderId(String accountHolderId);

	Mono<BankAccount> updateBankAccountState(String bankAccountNumber, BankAccountState bankAccountState);

	Flux<BankAccount> findAll();

	Flux<BankAccount> findByBankAccountType(String bankAccountType);

	Mono<BankAccount> findById(String id);

}
