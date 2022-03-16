package pe.com.project1.ms.application.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import pe.com.project1.ms.application.BankAccountService;
import pe.com.project1.ms.application.exceptions.ConflictException;
import pe.com.project1.ms.application.model.BankAccountRepository;
import pe.com.project1.ms.domain.bank.account.BankAccount;
import pe.com.project1.ms.domain.bank.account.BankAccountType;
import pe.com.project1.ms.domain.bank.transaction.BankingTransaction;
import pe.com.project1.ms.domain.bank.transaction.BankingTransactionType;
import pe.com.project1.ms.domain.customer.Customer;
import pe.com.project1.ms.domain.customer.CustomerType;
import pe.com.project1.ms.infraestructure.rest.request.UpdateStateAccountRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private WebClient customerWebClient;

	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	@Override
	public Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber) {
		return bankAccountRepository.findByBankAccountNumber(bankAccountNumber);
	}

	@Override
	public Mono<BankAccount> save(BankAccount bankAccount) {
		final String accountHolderId = bankAccount.getAccountHolderId();
		return customerWebClient
				.get()
				.uri(accountHolderId)
				.retrieve()
				.bodyToMono(Customer.class)
				.map(customer -> customer.getCustomerType())
				.flatMap(customerType -> this.assertThatCustomerCanCreateAnBankAccount(customerType, bankAccount))
				.then(bankAccountRepository.save(bankAccount));
	}
	
	private Mono<Void> assertThatCustomerCanCreateAnBankAccount(CustomerType customerType, BankAccount bankAccount) {
		log.debug("assertThatCustomerCanCreateAnBankAccount(customerType: {}, bankAccount: {})", customerType,
				bankAccount);
		if (customerType.equals(CustomerType.ENTERPRISE)) {
			List<BankAccountType> accountsWithConstraints = List.of(BankAccountType.SAVING, BankAccountType.FIXED_TERM);
			return this.assertThatCustomerDontHaveAnBankAccount(customerType, bankAccount, accountsWithConstraints);
		} else if (customerType.equals(CustomerType.PERSONAL)) {
			List<BankAccountType> accountsWithConstraints = List.of(BankAccountType.CURRENT, BankAccountType.FIXED_TERM);
			return this.assertThatCustomerDontHaveAnBankAccount(customerType, bankAccount, accountsWithConstraints);
		} else {
			return Mono.error(new RuntimeException("Tipo de cliente no soportado!!"));
		}
	}
	
	private Mono<Void> assertThatCustomerDontHaveAnBankAccount(CustomerType customerType, BankAccount bankAccount, List<BankAccountType> accountsWithConstraints) {
		return 	bankAccountRepository
				.findByAccountHolderId(bankAccount.getAccountHolderId())
				.filter(existingBankAccount -> this.checkBankAccountConstraint(existingBankAccount, bankAccount, accountsWithConstraints))
				.count()
				.flatMap(numberOfBankAccountsWithConstraint -> {
					if(numberOfBankAccountsWithConstraint > 0) {
						return Mono.error(new ConflictException("El cliente ya tiene una cuenta bancaria del tipo: " + bankAccount.getBankAccountType()));
					}
					return Mono.empty();
				});
	}
	
	private boolean checkBankAccountConstraint(BankAccount existingBankAccount, BankAccount bankAccount,
			List<BankAccountType> accountsWithConstraints) {
		BankAccountType existingBankAccountType = existingBankAccount.getBankAccountType();
		BankAccountType bankAccountType = bankAccount.getBankAccountType();
		boolean alreadyHaveAnAccountBank = bankAccountType.equals(existingBankAccount.getBankAccountType());
		boolean complyConstraint = accountsWithConstraints.indexOf(existingBankAccountType) != -1
				&& accountsWithConstraints.indexOf(bankAccountType) != -1;
		return alreadyHaveAnAccountBank || complyConstraint;
	}
	
	@Override
	public Mono<BankAccount> update(BankAccount bankAccount, String bankAccountNumber) {
		return bankAccountRepository.update(bankAccount, bankAccountNumber);
	}

	@Override
	public Mono<BankAccount> updateBankAccountState(String bankAccountNumber,
			UpdateStateAccountRequest updateStateAccountRequest) {
		log.debug("UpdateStateAccount {}", updateStateAccountRequest);
		return bankAccountRepository.updateBankAccountState(bankAccountNumber,
				updateStateAccountRequest.getBankAccountState());
	}

	@Override
	public Flux<BankAccount> findByAccountHolderId(String accountHolderId) {
		return bankAccountRepository.findByAccountHolderId(accountHolderId);
	}

	@Override
	public Flux<BankAccount> findAll() {
		return bankAccountRepository
				.findAll();
	}

	@Override
	public Flux<BankAccount> findByBankAccountType(String bankAccountType) {
		return bankAccountRepository.findByBankAccountType(bankAccountType);
	}

	@Override
	public Mono<BankAccount> updateBalance(BankAccount bankAccount, BankingTransaction bankingTransaction) {
		BankingTransactionType bankingTransactionType = bankingTransaction.getBankingTransactionType();
		BigDecimal amount = bankingTransaction.getAmount();
		if (bankingTransactionType.equals(BankingTransactionType.WITHDRAWAL)) {
			bankAccount.withdraw(amount);
		} else if (bankingTransactionType.equals(BankingTransactionType.DEPOSIT)) {
			bankAccount.deposit(amount);
		} else {
			return Mono.error(new RuntimeException("Transaccion no valida"));
		}
		return bankAccountRepository.save(bankAccount);
	}

}
