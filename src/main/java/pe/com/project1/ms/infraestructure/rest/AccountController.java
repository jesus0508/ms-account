package pe.com.project1.ms.infraestructure.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.project1.ms.application.AccountOperation;
import pe.com.project1.ms.domain.Account;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private AccountOperation accountOperation;

	@GetMapping
	public Mono<Account> getAccountByAccountNumber(@RequestParam String accountNumber) {
		return accountOperation.findByAccountNumber(accountNumber);
	}

	@PostMapping
	public Mono<Account> post(@RequestBody Account account) {
		return accountOperation.save(account);
	}

}
