package tech.marie.currency_conversion.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversionResponse {
    @Schema(description = "Code de la devise source", example = "EUR")
    private String sourceCurrency;

    @Schema(description = "Code de la devise cible", example = "USD")
    private String targetCurrency;

    @Schema(description = "Montant dans la devise source", example = "100.50")
    private BigDecimal sourceAmount;

    @Schema(description = "Montant converti dans la devise cible", example = "110.23")
    private BigDecimal convertedAmount;

    @Schema(description = "Taux de change utilisé", example = "1.0972")
    private BigDecimal exchangeRate;
}
