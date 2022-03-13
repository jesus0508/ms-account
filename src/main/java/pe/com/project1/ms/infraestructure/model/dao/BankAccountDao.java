package pe.com.project1.ms.infraestructure.model.dao;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.project1.ms.domain.BankAccount;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("account")
public class BankAccountDao {
	@Id
	private String id;
	private String bankAccountNumber;
	private BigDecimal balance;
	private boolean isLocked;
	private String accountHolderId;

	public BankAccountDao(BankAccount account) {
		this.id = account.getId();
		this.bankAccountNumber = account.getBankAccountNumber();
		this.balance = account.getBalance();
		this.isLocked = account.isLocked();
		this.accountHolderId = account.getAccountHolderId();
	}

}
