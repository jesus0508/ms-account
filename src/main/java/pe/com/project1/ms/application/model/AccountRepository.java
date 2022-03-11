package pe.com.project1.ms.application.model;

import pe.com.project1.ms.domain.Account;
import reactor.core.publisher.Mono;

public interface AccountRepository {
	public Mono<Account> findByAccountNumber(String accountNumber);

	public Mono<Account> save(Account account);
}
