package pe.com.project1.ms.application.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.com.project1.ms.application.BankAccountService;
import pe.com.project1.ms.application.BankingTransactionService;
import pe.com.project1.ms.application.model.BankingTransactionRepository;
import pe.com.project1.ms.domain.bank.account.BankAccount;
import pe.com.project1.ms.domain.bank.transaction.BankingTransaction;
import pe.com.project1.ms.domain.bank.transaction.BankingTransactionType;
import pe.com.project1.ms.infraestructure.rest.request.BankingTransactionRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BankingTransactionServiceImpl implements BankingTransactionService {

	@Autowired
	private BankingTransactionRepository bankingTransactionRepository;
	@Autowired
	private BankAccountService bankAccountService;

	@Override
	public Mono<BankingTransaction> save(BankingTransactionRequest bankingTransactionRequest) {
		BankingTransaction bankingTransaction = bankingTransactionRequest.toBankingTransaction();
		return this.asserThatCanMakeTransaction(bankingTransaction)
				.doOnNext(bt -> log.debug(bt.toString()))
				.then(bankingTransactionRepository.save(bankingTransaction));
	}
		
	private Mono<BankAccount> asserThatCanMakeTransaction(BankingTransaction bankingTransaction) {
		return bankAccountService
				.findByBankAccountNumber(bankingTransaction.getFromBankAccount())
				.switchIfEmpty(Mono.error(new RuntimeException("Cuenta no existe")))
				.filter(account -> this.checkBalance(account, bankingTransaction.getBankingTransactionType(), bankingTransaction.getAmount()))
				.switchIfEmpty(Mono.error(new RuntimeException("Fondos insuficientes")))
				.flatMap(bankAccount -> bankAccountService.updateBalance(bankAccount, bankingTransaction));
	}
	
	private boolean checkBalance(BankAccount bankAccount, BankingTransactionType bankingTransactionType, BigDecimal amount) {
		return bankingTransactionType.equals(BankingTransactionType.DEPOSIT) || bankAccount.getBalance().compareTo(amount) >= 0;
	}

	@Override
	public Flux<BankingTransaction> findAll() {
		return bankingTransactionRepository.findAll();
	}

	@Override
	public Flux<BankingTransaction> findAllByBankAccountNumber(String bankAccountNumber) {
		return bankingTransactionRepository.findAllByBankAccountNumber(bankAccountNumber);
	}
	
}
