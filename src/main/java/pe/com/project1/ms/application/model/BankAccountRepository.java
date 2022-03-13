package pe.com.project1.ms.application.model;

import pe.com.project1.ms.domain.BankAccount;
import pe.com.project1.ms.domain.BankAccountState;
import pe.com.project1.ms.domain.BankingTransactionHistory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountRepository {
	Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber);

	Mono<BankAccount> save(BankAccount bankAccount);

	Mono<BankAccount> update(BankAccount bankAccount, String bankAccountNumber);

	Flux<BankAccount> findByAccountHolderId(String accountHolderId);

	Flux<BankingTransactionHistory> getBankingTransactionHistoryById(String id);

	Mono<BankAccount> updateBankAccountState(String bankAccountNumber, BankAccountState bankAccountState);

	Flux<BankAccount> findAll();
}
