package pe.com.project1.ms.infraestructure.repository.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.com.project1.ms.application.model.BankAccountRepository;
import pe.com.project1.ms.domain.BankAccount;
import pe.com.project1.ms.infraestructure.model.dao.BankAccountDao;
import reactor.core.publisher.Mono;

@Repository
public class BankAccountReactiveCrudRepository implements BankAccountRepository {

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

	private BankAccount mapBankAccountDaoToBankAccount(BankAccountDao bankAccountDao) {
		return BankAccount.builder()
				.bankAccountNumber(bankAccountDao.getBankAccountNumber())
				.balance(bankAccountDao.getBalance())
				.isLocked(bankAccountDao.isLocked())
				.accountHolderId(bankAccountDao.getAccountHolderId())
				.build();
	}

	@Override
	public Mono<BankAccount> update(BankAccount banckAccount, String bankAccountNumber) {
		return null;
	}

}
