package pe.com.project1.ms.infraestructure.model.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.project1.ms.domain.bank.transaction.BankingTransaction;
import pe.com.project1.ms.domain.bank.transaction.BankingTransactionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("bankingTransaction")
public class BankingTransactionDao {
	@Id
	private String id;
	private String fromBankAccount;
	private String toBankAccount;
	private BigDecimal amount;
	private LocalDateTime ocurredAt;
	private BankingTransactionType bankingTransactionType;
	
	public BankingTransactionDao(BankingTransaction bankingTransaction) {
		this.fromBankAccount = bankingTransaction.getFromBankAccount();
		this.toBankAccount = bankingTransaction.getToBankAccount();
		this.amount = bankingTransaction.getAmount();
		this.ocurredAt = bankingTransaction.getOcurredAt();
		this.bankingTransactionType = bankingTransaction.getBankingTransactionType();
	}
	
}
