package pe.com.project1.ms.infraestructure.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BankAccountRouter {

    private static final String BANK_ACCOUNTS = "/bank-accounts";
    private static final String BANK_ACCOUNT_ID = "/{id}";
    private static final String BAN_ACCOUNT_STATE = "";

    @Bean
    public RouterFunction<ServerResponse> routes(BankAccountHandler bankAccountHandler) {
        return route().path(BANK_ACCOUNTS, builder ->
                builder.POST("", bankAccountHandler::postBankAccount)
                        .GET(BANK_ACCOUNT_ID, bankAccountHandler::getBankAccountById)
                        .GET("", bankAccountHandler::getAllBankAccountsByAccountHolderId)
                        .PATCH("/{id}/state", bankAccountHandler::patchBankAccountState)
        ).build();
    }
}

