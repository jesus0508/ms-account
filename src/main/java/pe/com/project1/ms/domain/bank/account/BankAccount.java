package pe.com.project1.ms.domain.bank.account;

import lombok.*;
import pe.com.project1.ms.domain.bank.transaction.BankingTransaction;

import java.math.BigDecimal;
import java.util.List;

@ToString
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    private String id;
    private String bankAccountNumber;
    private BigDecimal balance;
    private String accountHolderId;
    private BankAccountType bankAccountType;
    private BankAccountState bankAccountState;
    private BankAccountTerms bankAccountTerms;
    private List<BankingTransaction> bankingTransactions;

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

}
