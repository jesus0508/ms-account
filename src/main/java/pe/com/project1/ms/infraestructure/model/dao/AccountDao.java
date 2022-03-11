package pe.com.project1.ms.infraestructure.model.dao;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import pe.com.project1.ms.domain.Account;

@Data
@Document("account")
public class AccountDao {
	@Id
	private String id;
	private String accountNumber;
	private BigDecimal balance;
	private boolean isLocked;
	private String customerId;
	
	public AccountDao(Account account) {
		this.id = account.getId();
		this.accountNumber = account.getAccountNumber();
		this.balance = account.getBalance();
		this.isLocked = account.isLocked();
		this.customerId = account.getCustomerId();
	}
	
}
