package pe.com.project1.ms.domain.bank.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankingTransaction {

	private String id;
	private String fromBankAccount;
	private String toBankAccount;
	private BigDecimal amount;
	private LocalDateTime ocurredAt;
	private BankingTransactionType bankingTransactionType;
	
	public BankingTransaction(String fromBankAccount, String toBankAccount, BigDecimal amount, LocalDateTime ocurredAt,
			BankingTransactionType bankingTransactionType) {
		this.fromBankAccount = fromBankAccount;
		this.toBankAccount = toBankAccount;
		this.amount = amount;
		this.ocurredAt = ocurredAt;
		this.bankingTransactionType = bankingTransactionType;
	}
}
