package pe.com.project1.ms.infraestructure.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import pe.com.project1.ms.application.FindBankAccountUseCase;
import pe.com.project1.ms.application.OpenBankAccountUseCase;
import pe.com.project1.ms.application.UpdateBankAccountUseCase;

@WebFluxTest
@ContextConfiguration(classes = {BankAccountRouter.class, BankAccountHandler.class})
@ActiveProfiles("local")
class BankAccountControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private FindBankAccountUseCase findBankAccountUseCase;
    @MockBean
    private OpenBankAccountUseCase openBankAccountUseCase;
    @MockBean
    private UpdateBankAccountUseCase updateBankAccountUseCase;

    @Test
    void testGetAll() {
//		final Flux<BankAccount> bankAccountFlux = Flux.just(new BankAccount());
//		
//		when(findBankAccountService.findAll()).thenReturn(bankAccountFlux);
//		
//		webTestClient.get().uri("/bank-accounts")
//			.exchange()
//			.expectStatus().isOk()
//			.expectBodyList(BankAccount.class)
//			.value(Assertions::assertNotNull)
//			.value(b -> assertEquals(1, b.size()));
    }

}
