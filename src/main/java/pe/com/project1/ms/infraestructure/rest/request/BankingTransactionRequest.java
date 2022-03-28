package pe.com.project1.ms.infraestructure.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.project1.ms.domain.bank.transaction.BankingTransaction;
import pe.com.project1.ms.domain.bank.transaction.BankingTransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankingTransactionRequest {
    private String fromBankAccount;
    private String toBankAccount;
    private BigDecimal amount;
    private BankingTransactionType bankingTransactionType;

    public BankingTransaction toBankingTransaction() {
        return new BankingTransaction(fromBankAccount, toBankAccount, amount, LocalDateTime.now(),
                bankingTransactionType);
    }
}
