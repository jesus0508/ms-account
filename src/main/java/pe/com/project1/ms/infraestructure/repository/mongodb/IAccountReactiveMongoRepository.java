package pe.com.project1.ms.infraestructure.repository.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import pe.com.project1.ms.infraestructure.model.dao.AccountDao;
import reactor.core.publisher.Mono;

public interface IAccountReactiveMongoRepository extends ReactiveMongoRepository<AccountDao, String> {

	Mono<AccountDao> findByAccountNumber(String accountNumber);

}
