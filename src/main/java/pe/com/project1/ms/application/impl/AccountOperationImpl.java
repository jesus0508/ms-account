package pe.com.project1.ms.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.project1.ms.application.AccountOperation;
import pe.com.project1.ms.application.model.AccountRepository;
import pe.com.project1.ms.domain.Account;
import reactor.core.publisher.Mono;

@Service
public class AccountOperationImpl implements AccountOperation {
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Mono<Account> findByAccountNumber(String accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber);
	}

	@Override
	public Mono<Account> save(Account account) {
		return accountRepository.save(account);
	}
}
