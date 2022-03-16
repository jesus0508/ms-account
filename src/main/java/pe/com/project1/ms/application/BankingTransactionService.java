package pe.com.project1.ms.application;

import pe.com.project1.ms.domain.bank.transaction.BankingTransaction;
import pe.com.project1.ms.infraestructure.rest.request.BankingTransactionRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankingTransactionService {
	Mono<BankingTransaction> save(BankingTransactionRequest bankingTransactionRequest);

	Flux<BankingTransaction> findAll();

	Flux<BankingTransaction> findAllByBankAccountNumber(String bankAccountId);
}
