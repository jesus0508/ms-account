package pe.com.project1.ms.application.persistence;

import pe.com.project1.ms.domain.bank.transaction.BankingTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankingTransactionRepository {

    Mono<BankingTransaction> save(BankingTransaction bankingTransaction);

    Flux<BankingTransaction> findAll();

    Flux<BankingTransaction> findAllByBankAccountNumber(String bankAccountNumber);

}
