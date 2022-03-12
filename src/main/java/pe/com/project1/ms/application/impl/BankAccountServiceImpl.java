package pe.com.project1.ms.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.project1.ms.application.BankAccountService;
import pe.com.project1.ms.application.model.BankAccountRepository;
import pe.com.project1.ms.domain.BankAccount;
import reactor.core.publisher.Mono;

@Service
public class BankAccountServiceImpl implements BankAccountService {
	@Autowired
	private BankAccountRepository accountRepository;

	@Override
	public Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber) {
		return accountRepository.findByBankAccountNumber(bankAccountNumber);
	}

	@Override
	public Mono<BankAccount> save(BankAccount bankAccount) {
		return accountRepository.save(bankAccount);
	}

	@Override
	public Mono<BankAccount> update(BankAccount banckAccount, String bankAccountNumber) {
		return null;
	}
}
