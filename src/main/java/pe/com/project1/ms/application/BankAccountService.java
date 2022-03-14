package pe.com.project1.ms.application;

import pe.com.project1.ms.domain.bank.account.BankAccount;
import pe.com.project1.ms.domain.bank.transaction.BankingTransactionHistory;
import pe.com.project1.ms.infraestructure.rest.request.UpdateStateAccountRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountService {
	
	Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber);

	Mono<BankAccount> save(BankAccount bankAccount);

	Mono<BankAccount> update(BankAccount banckAccount, String bankAccountNumber);

	Flux<BankAccount> findByAccountHolderId(String accountHolderId);

	Flux<BankingTransactionHistory> getBankingTransactionHistoryById(String id);

	Mono<BankAccount> updateBankAccountState(String bankAccountNumber, UpdateStateAccountRequest updateStateAccountRequest);

	Flux<BankAccount> findAll();
	
	Flux<BankAccount> findByBankAccountType(String bankAccountType);

}
