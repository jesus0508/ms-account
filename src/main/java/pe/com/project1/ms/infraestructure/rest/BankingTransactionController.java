package pe.com.project1.ms.infraestructure.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.project1.ms.application.BankingTransactionService;
import pe.com.project1.ms.domain.bank.transaction.BankingTransaction;
import pe.com.project1.ms.infraestructure.rest.request.BankingTransactionRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/banking-transactions")
public class BankingTransactionController {
	
	@Autowired
	private BankingTransactionService bankingTransactionService;
	
	@PostMapping
	public Mono<BankingTransaction> postBankingTransaction(@RequestBody BankingTransactionRequest bankingTransactionRequest) {
		return bankingTransactionService.save(bankingTransactionRequest);
	}
	
	@GetMapping("/bank-account-number/{bankAccountNumber}")
	public Flux<BankingTransaction> getAllBankingTransaction(@PathVariable String bankAccountNumber) {
		return bankingTransactionService.findAllByBankAccountNumber(bankAccountNumber);
	}
	
}
