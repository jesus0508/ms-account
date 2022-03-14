package pe.com.project1.ms.domain.bank.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BankAccountType {
	SAVING("SAVING"),
	CURRENT("CURRENT"),
	FIXED_TERM("FIXED_TERM");
	private String value;
}
