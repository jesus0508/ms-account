package pe.com.project1.ms.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.com.project1.ms.application.BankAccountService;
import pe.com.project1.ms.application.model.BankAccountRepository;
import pe.com.project1.ms.domain.BankAccount;
import pe.com.project1.ms.domain.BankingTransactionHistory;
import pe.com.project1.ms.infraestructure.rest.request.UpdateStateAccountRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BankAccountServiceImpl implements BankAccountService {
	
	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Override
	public Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber) {
		return bankAccountRepository.findByBankAccountNumber(bankAccountNumber);
	}

	@Override
	public Mono<BankAccount> save(BankAccount bankAccount) {
		return bankAccountRepository.save(bankAccount);
	}

	@Override
	public Mono<BankAccount> update(BankAccount bankAccount, String bankAccountNumber) {
		return bankAccountRepository.update(bankAccount, bankAccountNumber);
	}

	@Override
	public Mono<BankAccount> updateBankAccountState(String bankAccountNumber, UpdateStateAccountRequest updateStateAccountRequest) {
		log.debug("UpdateStateAccount {}", updateStateAccountRequest);
		return bankAccountRepository.updateBankAccountState(bankAccountNumber, updateStateAccountRequest.getBankAccountState());
	}

	@Override
	public Flux<BankAccount> findByAccountHolderId(String accountHolderId) {
		return bankAccountRepository.findByAccountHolderId(accountHolderId);
	}

	@Override
	public Flux<BankingTransactionHistory> getBankingTransactionHistoryById(String id) {
		return bankAccountRepository.getBankingTransactionHistoryById(id);
	}

	@Override
	public Flux<BankAccount> findAll() {
		return bankAccountRepository.findAll();
	}
}
