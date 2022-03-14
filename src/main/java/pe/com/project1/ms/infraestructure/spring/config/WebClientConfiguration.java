package pe.com.project1.ms.infraestructure.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
	
	private static final String MS_CUSTOMER_BASE_URL = "http://localhost:9081/customers/";
	
    @Bean
    public WebClient customerWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .clone()
                .baseUrl(MS_CUSTOMER_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
