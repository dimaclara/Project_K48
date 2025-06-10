package tech.marie.currency_conversion.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tech.marie.currency_conversion.client.ExchangeRateClient;
import tech.marie.currency_conversion.exception.CurrencyNotSupportedException;
import tech.marie.currency_conversion.model.ConversionRequest;
import tech.marie.currency_conversion.model.ConversionResponse;
import tech.marie.currency_conversion.model.ExchangeRateResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversionService {

    private final ExchangeRateClient exchangeRateClient;

    public Mono<ConversionResponse> convertCurrent(ConversionRequest conversionRequest) {
        log.info("Converting {} {} to {}", conversionRequest.getAmount(),
                conversionRequest.getSourceCurrency(), conversionRequest.getTargetCurrency());

        // Currency Validation
        validateCurrency(conversionRequest.getSourceCurrency());
        validateCurrency(conversionRequest.getTargetCurrency());

        // Appel non bloquant à l’API des taux
        return exchangeRateClient.getLatestRates(conversionRequest.getSourceCurrency())
                .map(rateResponse -> {
                    if (!rateResponse.getRates().containsKey(conversionRequest.getTargetCurrency())) {
                        throw new CurrencyNotSupportedException("La devise cible n'est pas supportée: "
                                + conversionRequest.getTargetCurrency());
                    }

                    BigDecimal exchangeRate = BigDecimal.valueOf(rateResponse.getRates()
                            .get(conversionRequest.getTargetCurrency()));
                    BigDecimal convertedAmount = conversionRequest.getAmount()
                            .multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);

                    return ConversionResponse.builder()
                            .sourceCurrency(conversionRequest.getSourceCurrency())
                            .targetCurrency(conversionRequest.getTargetCurrency())
                            .sourceAmount(conversionRequest.getAmount())
                            .convertedAmount(convertedAmount)
                            .exchangeRate(exchangeRate)
                            .build();
                });
    }

    private void validateCurrency(String currencyCode) {
        if (currencyCode == null || currencyCode.length() != 3) {
            throw new CurrencyNotSupportedException("Code de devise invalide: " + currencyCode);
        }
    }
}
