package pe.com.project1.ms.infraestructure.repository.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.com.project1.ms.application.model.AccountRepository;
import pe.com.project1.ms.domain.Account;
import pe.com.project1.ms.infraestructure.model.dao.AccountDao;
import reactor.core.publisher.Mono;

@Repository
public class AccountReactiveCrudRepository implements AccountRepository {

	@Autowired
	private IAccountReactiveMongoRepository accountReactiveMongoRepository;

	@Override
	public Mono<Account> findByAccountNumber(String accountNumber) {
		return accountReactiveMongoRepository
				.findByAccountNumber(accountNumber)
				.map(this::mapAccountDaoToAccount);
	}

	@Override
	public Mono<Account> save(Account account) {
		return accountReactiveMongoRepository
				.save(new AccountDao(account))
				.map(this::mapAccountDaoToAccount);
	}

	private Account mapAccountDaoToAccount(AccountDao accountDao) {
		return Account.builder()
				.accountNumber(accountDao.getAccountNumber())
				.balance(accountDao.getBalance())
				.isLocked(accountDao.isLocked())
				.customerId(accountDao.getCustomerId()).build();
	}

}
