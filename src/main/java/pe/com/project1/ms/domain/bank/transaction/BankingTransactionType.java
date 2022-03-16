package pe.com.project1.ms.domain.bank.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BankingTransactionType {
	DEPOSIT("DEPOSIT"),
	WITHDRAWAL("WITHDRAWAL"),
	TRANSFER("TRANSFER");
	private String value;

}
