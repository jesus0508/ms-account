package pe.com.project1.ms.domain.bank.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BankAccountState {
	LOCK("LOCK"),
	UNLOCK("UNLOCK"),
	DELETED("DELETED");
	private String value;
}
