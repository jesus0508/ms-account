package pe.com.project1.ms.domain;

import java.util.LinkedList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class BankingTransactionHistory extends LinkedList<BankingTransaction> {
	private static final long serialVersionUID = 1L;
}
