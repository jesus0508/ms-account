package pe.com.project1.ms.infraestructure.repository.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import pe.com.project1.ms.domain.bank.transaction.BankingTransaction;
import pe.com.project1.ms.infraestructure.model.dao.BankingTransactionDao;
import reactor.core.publisher.Flux;

public interface IBankingTransactionReactiveMongoRepository extends ReactiveMongoRepository<BankingTransactionDao, String>{

	Flux<BankingTransaction> findByFromBankAccount(String fromBankAccount);

}
