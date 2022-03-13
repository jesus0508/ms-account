package pe.com.project1.ms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BankAccountState {
	LOCK("LOCK"),
	UNLOCK("UNLOCK");
	
	private String value;
}
