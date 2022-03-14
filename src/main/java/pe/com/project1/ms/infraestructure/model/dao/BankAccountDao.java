package pe.com.project1.ms.infraestructure.model.dao;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.project1.ms.domain.bank.account.BankAccount;
import pe.com.project1.ms.domain.bank.account.BankAccountState;
import pe.com.project1.ms.domain.bank.account.BankAccountTerms;
import pe.com.project1.ms.domain.bank.account.BankAccountType;
import pe.com.project1.ms.domain.bank.transaction.BankingTransactionHistory;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("account")
public class BankAccountDao {
	@Id
	private String id;
	private String bankAccountNumber;
	private BigDecimal balance;
	private String accountHolderId;
	private BankAccountType bankAccountType;
	private BankAccountState bankAccountState;
	private BankAccountTerms bankAccountTerms;
	private BankingTransactionHistory bankingTransactionHistory;

	public BankAccountDao(BankAccount account) {
		this.id = account.getId();
		this.bankAccountNumber = account.getBankAccountNumber();
		this.balance = account.getBalance();
		this.accountHolderId = account.getAccountHolderId();
		this.bankAccountType = account.getBankAccountType();
		this.bankAccountState = account.getBankAccountState();
		this.bankAccountTerms = account.getBankAccountTerms();
		this.bankingTransactionHistory = account.getBankingTransactionHistory();
	}
}
