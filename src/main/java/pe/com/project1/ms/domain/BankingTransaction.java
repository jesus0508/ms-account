package pe.com.project1.ms.domain;

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
	private String toBankAccountId;
	private String fromBankAccountId;
	private BigDecimal mount;
	private LocalDateTime ocurredAt;
	private BankingTransactionType bankingTransaction;
}
