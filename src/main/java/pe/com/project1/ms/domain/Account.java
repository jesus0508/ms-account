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
public class Account {
	private String id;
	private String accountNumber;
	private BigDecimal balance;
	private boolean isLocked;
	private String customerId;
}
