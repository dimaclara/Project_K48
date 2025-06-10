package tech.marie.currency_conversion.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tech.marie.currency_conversion.exception.ExternalApiException;
import tech.marie.currency_conversion.model.ExchangeRateResponse;

@Component
@Slf4j
public class ExchangeRateClient {
    private final WebClient webClient;
    private final String apiKey;

    public ExchangeRateClient(WebClient.Builder webClientBuilder,
                              @Value("${exchange.api.url}") String apiUrl,
                              @Value("${exchange.api.key}") String apiKey) {
        // Ne pas utiliser l'URL complète comme baseUrl, seulement le domaine
        this.webClient = webClientBuilder.baseUrl("https://v6.exchangerate-api.com").build();
        this.apiKey = apiKey;
    }

    public Mono<ExchangeRateResponse> getLatestRates(String baseCurrency) {
        if (baseCurrency == null || baseCurrency.isEmpty()) {
            baseCurrency = "USD"; // Valeur par défaut
        }

        log.info("Fetching latest rates from external API for base currency: {}", baseCurrency);

        // Construire l'URL correctement selon la documentation de l'API
        String path = "/v6/" + apiKey + "/latest/" + baseCurrency;

        return webClient.get()
                .uri(path)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(error -> Mono.error(new ExternalApiException("Error from external API: " + error)))
                )
                .bodyToMono(ExchangeRateResponse.class);

    }
}