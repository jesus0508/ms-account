package pe.com.project1.ms.infraestructure.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.project1.ms.application.BankAccountService;
import pe.com.project1.ms.domain.BankAccount;
import pe.com.project1.ms.domain.BankingTransactionHistory;
import pe.com.project1.ms.infraestructure.rest.request.UpdateStateAccountRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {
	
	@Autowired
	private BankAccountService bankAccountService;

	@GetMapping()
	public Flux<BankAccount> getAll() {
		return bankAccountService.findAll();
	}
	
	@GetMapping("/bank-account-number/{bankAccountNumber}")
	public Mono<BankAccount> getByBankAccountNumber(@PathVariable String bankAccountNumber) {
		return bankAccountService.findByBankAccountNumber(bankAccountNumber);
	}

	@PostMapping
	public Mono<BankAccount> postBankAccount(@RequestBody BankAccount bankAccount) {
		return bankAccountService.save(bankAccount);
	}

	@GetMapping("/account-holder-id/{accountHolderId}")
	public Flux<BankAccount> getBankAccountsByAccountHolderId(@PathVariable String accountHolderId) {
		return bankAccountService.findByAccountHolderId(accountHolderId);
	}

	@GetMapping("/{id}/bank-transaction-history")
	public Flux<BankingTransactionHistory> getBankingTransactionHistoryByAccountHolderId(@PathVariable String id) {
		return bankAccountService.getBankingTransactionHistoryById(id);
	}
	
	@PatchMapping("/bank-account-number/{bankAccountNumber}")
	public Mono<BankAccount> patchBankAccountState(@PathVariable String bankAccountNumber, @RequestBody UpdateStateAccountRequest updateStateAccountRequest){
		return bankAccountService.updateBankAccountState(bankAccountNumber, updateStateAccountRequest);
	}

}
