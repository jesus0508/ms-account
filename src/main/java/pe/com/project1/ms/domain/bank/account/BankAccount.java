package pe.com.project1.ms.domain.bank.account;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.com.project1.ms.domain.bank.transaction.BankingTransactionHistory;

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
	private BankingTransactionHistory bankingTransactionHistory;
}
