package pe.com.project1.ms.domain.bank.account;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pe.com.project1.ms.domain.bank.transaction.BankingTransaction;

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
