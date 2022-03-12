package pe.com.project1.ms.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
	private String id;
	private String bankAccountNumber;
	private BigDecimal balance;
	private boolean isLocked;
	private String accountHolderId;
}
