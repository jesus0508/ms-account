package pe.com.project1.ms.infraestructure.repository.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import pe.com.project1.ms.application.exceptions.NotFoundException;
import pe.com.project1.ms.application.model.BankAccountRepository;
import pe.com.project1.ms.domain.bank.account.BankAccount;
import pe.com.project1.ms.domain.bank.account.BankAccountState;
import pe.com.project1.ms.infraestructure.model.dao.BankAccountDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class BankAccountReactiveMongoRepository implements BankAccountRepository {

	@Autowired
	private IBankAccountReactiveMongoRepository bankAccountReactiveMongoRepository;

	@Override
	public Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber) {
		return bankAccountReactiveMongoRepository
				.findByBankAccountNumber(bankAccountNumber)
				.map(this::mapBankAccountDaoToBankAccount);
	}

	@Override
	public Mono<BankAccount> save(BankAccount bankAccount) {
		return bankAccountReactiveMongoRepository
				.save(new BankAccountDao(bankAccount))
				.map(this::mapBankAccountDaoToBankAccount);
	}

	@Override
	public Mono<BankAccount> updateBankAccountState(String bankAccountNumber, BankAccountState bankAccountState) {
		return bankAccountReactiveMongoRepository
				.findByBankAccountNumber(bankAccountNumber)
				.switchIfEmpty(Mono.error(new NotFoundException("No existe cuenta bancaria: " + bankAccountNumber)))
				.map(bankAccountDao -> this.updateState(bankAccountDao, bankAccountState))
				.flatMap(bankAccountDaoMono -> bankAccountDaoMono).map(this::mapBankAccountDaoToBankAccount);
	}

	@Override
	public Flux<BankAccount> findByAccountHolderId(String accountHolderId) {
		return bankAccountReactiveMongoRepository
				.findByAccountHolderId(accountHolderId)
				.doOnNext(account -> log.debug("Se encontro la siguiente cuenta: {}", account))
				.map(this::mapBankAccountDaoToBankAccount);
	}

	@Override
	public Mono<BankAccount> update(BankAccount bankAccount, String bankAccountNumber) {
		return null;
	}

	@Override
	public Flux<BankAccount> findAll() {
		return bankAccountReactiveMongoRepository.findAll().map(this::mapBankAccountDaoToBankAccount);
	}

	private Mono<BankAccountDao> updateState(BankAccountDao bankAccountDao, BankAccountState bankAccountState) {
		bankAccountDao.setBankAccountState(bankAccountState);
		return bankAccountReactiveMongoRepository.save(bankAccountDao);
	}

	private BankAccount mapBankAccountDaoToBankAccount(BankAccountDao bankAccountDao) {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setId(bankAccountDao.getId());
		bankAccount.setBankAccountNumber(bankAccountDao.getBankAccountNumber());
		bankAccount.setBalance(bankAccountDao.getBalance());
		bankAccount.setAccountHolderId(bankAccountDao.getAccountHolderId());
		bankAccount.setBankAccountType(bankAccountDao.getBankAccountType());
		bankAccount.setBankAccountState(bankAccountDao.getBankAccountState());
		bankAccount.setBankAccountTerms(bankAccountDao.getBankAccountTerms());
		bankAccount.setBankingTransactions(bankAccountDao.getBankingTransactions());
		return bankAccount;
	}

	private BankAccountDao mapBankAccountToBankAccountDao(BankAccount bankAccount) {
		BankAccountDao bankAccountDao = new BankAccountDao();
		bankAccountDao.setId(bankAccount.getId());
		bankAccountDao.setBankAccountNumber(bankAccount.getBankAccountNumber());
		bankAccountDao.setBalance(bankAccount.getBalance());
		bankAccountDao.setAccountHolderId(bankAccount.getAccountHolderId());
		bankAccountDao.setBankAccountType(bankAccount.getBankAccountType());
		bankAccountDao.setBankAccountState(bankAccount.getBankAccountState());
		bankAccountDao.setBankAccountTerms(bankAccount.getBankAccountTerms());
		bankAccountDao.setBankingTransactions(bankAccount.getBankingTransactions());
		return bankAccountDao;
	}

	@Override
	public Flux<BankAccount> findByBankAccountType(String bankAccountType) {
		return bankAccountReactiveMongoRepository
				.findByBankAccountType(bankAccountType)
				.map(this::mapBankAccountDaoToBankAccount);
	}

	@Override
	public Mono<BankAccount> findById(String id) {
		return bankAccountReactiveMongoRepository
				.findById(id)
				.map(this::mapBankAccountDaoToBankAccount);
	}

}
