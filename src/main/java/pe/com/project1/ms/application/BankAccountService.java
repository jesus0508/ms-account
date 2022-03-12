package pe.com.project1.ms.application;

import pe.com.project1.ms.domain.BankAccount;
import reactor.core.publisher.Mono;

public interface BankAccountService {
	public Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber);

	public Mono<BankAccount> save(BankAccount bankAccount);
	
	public Mono<BankAccount> update(BankAccount banckAccount, String bankAccountNumber);
}
