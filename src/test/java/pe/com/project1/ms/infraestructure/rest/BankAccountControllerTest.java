package pe.com.project1.ms.infraestructure.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import pe.com.project1.ms.application.FindBankAccountUseCase;
import pe.com.project1.ms.domain.bank.account.BankAccount;
import reactor.core.publisher.Flux;

@WebFluxTest
@ContextConfiguration(classes = {BankAccountHandler.class, BankAccountRouter.class})
class BankAccountControllerTest {

	@Autowired
	private WebTestClient webTestClient;
	@MockBean
	private FindBankAccountUseCase findBankAccountUseCase;
	
	@Test
	void testGetAll() {
		final Flux<BankAccount> bankAccountFlux = Flux.just(new BankAccount());
		
		when(findBankAccountUseCase.findAll()).thenReturn(bankAccountFlux);
		
		webTestClient.get().uri("/bank-accounts")
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(BankAccount.class)
			.value(Assertions::assertNotNull)
			.value(b -> assertEquals(1, b.size()));
	}

	@Test
	void testGetByBankAccountNumber() {

	}

	@Test
	void testPostBankAccount() {

	}

	@Test
	void testGetBankAccountsByBankAccountType() {

	}

	@Test
	void testGetBankAccountsByAccountHolderId() {

	}

	@Test
	void testPatchBankAccountState() {

	}

}
