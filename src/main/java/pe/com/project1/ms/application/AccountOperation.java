package pe.com.project1.ms.application;

import pe.com.project1.ms.domain.Account;
import reactor.core.publisher.Mono;

public interface AccountOperation {
	public Mono<Account> findByAccountNumber(String accountNumber);

	public Mono<Account> save(Account account);
}
