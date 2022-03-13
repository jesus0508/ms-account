package pe.com.project1.ms.infraestructure.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.project1.ms.application.BankAccountService;
import pe.com.project1.ms.domain.BankAccount;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;

	@GetMapping
	public Mono<BankAccount> getBankAccountByBankAccountNumber(@RequestParam String bankAccountNumber) {
		return bankAccountService.findByBankAccountNumber(bankAccountNumber);
	}

	@PostMapping
	public Mono<BankAccount> post(@RequestBody BankAccount bankAccount) {
		return bankAccountService.save(bankAccount);
	}

}
