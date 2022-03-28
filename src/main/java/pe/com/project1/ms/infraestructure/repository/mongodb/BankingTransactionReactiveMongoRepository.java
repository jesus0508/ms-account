package pe.com.project1.ms.infraestructure.repository.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.com.project1.ms.application.persistence.BankingTransactionRepository;
import pe.com.project1.ms.domain.bank.transaction.BankingTransaction;
import pe.com.project1.ms.infraestructure.model.dao.BankingTransactionDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class BankingTransactionReactiveMongoRepository implements BankingTransactionRepository {

    @Autowired
    private IBankingTransactionReactiveMongoRepository bankingTransactionReactiveMongoRepository;

    @Override
    public Mono<BankingTransaction> save(BankingTransaction bankingTransaction) {
        return bankingTransactionReactiveMongoRepository
                .save(new BankingTransactionDao(bankingTransaction))
                .map(this::mapBankingTransactionDaoToBankingTransaction);
    }

    private BankingTransaction mapBankingTransactionDaoToBankingTransaction(
            BankingTransactionDao bankingTransactionDao) {
        BankingTransaction bankingTransaction = new BankingTransaction();
        bankingTransaction.setId(bankingTransactionDao.getId());
        bankingTransaction.setFromBankAccount(bankingTransactionDao.getFromBankAccount());
        bankingTransaction.setToBankAccount(bankingTransactionDao.getToBankAccount());
        bankingTransaction.setAmount(bankingTransactionDao.getAmount());
        bankingTransaction.setOcurredAt(bankingTransactionDao.getOcurredAt());
        bankingTransaction.setBankingTransactionType(bankingTransactionDao.getBankingTransactionType());
        return bankingTransaction;
    }

    @Override
    public Flux<BankingTransaction> findAll() {
        return bankingTransactionReactiveMongoRepository
                .findAll()
                .map(this::mapBankingTransactionDaoToBankingTransaction);
    }

    @Override
    public Flux<BankingTransaction> findAllByBankAccountNumber(String bankAccountNumber) {
        return bankingTransactionReactiveMongoRepository.findByFromBankAccount(bankAccountNumber);
    }

}
