package pe.com.project1.ms.application.model;

import pe.com.project1.ms.domain.BankAccount;
import reactor.core.publisher.Mono;

public interface BankAccountRepository {
	public Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber);

	public Mono<BankAccount> save(BankAccount bankAccount);
	
	public Mono<BankAccount> update(BankAccount banckAccount, String bankAccountNumber);
}
