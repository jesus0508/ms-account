package pe.com.project1.ms.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.project1.ms.application.OpenBankAccountUseCase;
import pe.com.project1.ms.application.persistence.BankAccountRepository;
import pe.com.project1.ms.domain.bank.account.BankAccount;
import pe.com.project1.ms.domain.bank.account.BankAccountState;
import pe.com.project1.ms.domain.bank.account.BankAccountType;
import pe.com.project1.ms.infraestructure.rest.request.OpenAccountRequest;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenBankAccountService implements OpenBankAccountUseCase {

    private final BankAccountRepository bankAccountRepository;

    @Override
    public Mono<BankAccount> openBankAccount(OpenAccountRequest openAccountRequest) {
        String accountHolderId = openAccountRequest.getAccountHolderId();
        return Mono.when(this.assertThatCustomerDosentHaveDebt(accountHolderId), this.canOpenBankAccount(openAccountRequest))
                .then(Mono.just(openAccountRequest))
                .map(this::mapOpenAccountRequestToBankAccount)
                .flatMap(bankAccountRepository::save);
    }

    private Mono<Boolean> assertThatCustomerDosentHaveDebt(String accountHolderId) {
        log.debug(accountHolderId);
        Mono<Boolean> hasDebtMono = Mono.just(false);
        return hasDebtMono.flatMap(hastDebt -> {
            if (hastDebt) {
                return Mono.error(new RuntimeException("Solo se le permite abrir cuentas corrientes"));
            }
            return Mono.just(hastDebt);
        });
    }

    private Mono<Boolean> assertThatPersonalCustomerDosentHaveBankAccount(OpenAccountRequest openAccountRequest) {
        String customerId = openAccountRequest.getAccountHolderId();
        BankAccountType bankAccounType = openAccountRequest.getBankAccountType();
        return bankAccountRepository.existByAccountHolderIdAndAccountType(customerId, bankAccounType)
                .flatMap(hasAccount -> {
                    if (hasAccount) {
                    	log.debug("El cliente ya tiene una cuenta de este tipo");
                    	return Mono.error(new RuntimeException("El cliente ya tiene una cuenta de este tipo"));
                    }
                    return Mono.just(hasAccount);
                });
    }

    private Mono<Boolean> canOpenBankAccount(OpenAccountRequest openAccountRequest) {
        switch (openAccountRequest.getCustomerType()) {
            case ENTERPRISE:
                if (!openAccountRequest.getBankAccountType().equals(BankAccountType.CURRENT)) {
                    return Mono.error(new RuntimeException("Clientes Empresariales solo pueden abrir cuentas corrientes"));
                }
                return Mono.just(true);
            case ENTERPRISE_PYME:
                if (!openAccountRequest.getBankAccountType().equals(BankAccountType.CURRENT)) {
                    return Mono.error(new RuntimeException("Clientes Empresariales solo pueden abrir cuentas corrientes"));
                }
                return this.assertThatCustomerHasCreditCard(openAccountRequest.getAccountHolderId());
            case PERSONAL:
                return this.assertThatPersonalCustomerDosentHaveBankAccount(openAccountRequest);
            case PERSONAL_VIP:
                Mono<Boolean> hasAccountMono = this.assertThatPersonalCustomerDosentHaveBankAccount(openAccountRequest);
                Mono<Boolean> hasCreditCardMono = this.assertThatCustomerHasCreditCard(openAccountRequest.getAccountHolderId());
                return Mono.when(hasAccountMono, hasCreditCardMono)
                		.then(Mono.just(true));
            default:
                return Mono.error(new RuntimeException("Tipo de cliente no soportado!!"));
        }
    }

    private Mono<Boolean> assertThatCustomerHasCreditCard(String customerId) {
        log.debug(customerId);
        Mono<Boolean> hasCreditCardMono = Mono.just(true);
        return hasCreditCardMono.flatMap(hasCreditCard -> {
            if (!hasCreditCard) {
            	return Mono.error(new RuntimeException("Cliente no tiene una tarjeta de credito"));
            }
            return Mono.just(hasCreditCard);
        });
    }

    private BankAccount mapOpenAccountRequestToBankAccount(OpenAccountRequest openAccountRequest) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBankAccountNumber("123456678");
        bankAccount.setBalance(BigDecimal.ZERO);
        bankAccount.setAccountHolderId(openAccountRequest.getAccountHolderId());
        bankAccount.setBankAccountType(openAccountRequest.getBankAccountType());
        bankAccount.setBankAccountState(BankAccountState.UNLOCK);
        bankAccount.setBankAccountTerms(null);
        return bankAccount;
    }

}
