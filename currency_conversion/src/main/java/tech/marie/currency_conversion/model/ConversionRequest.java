package tech.marie.currency_conversion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ConversionRequest {
    //@Schema(description = "Code de la devise source (ex: EUR)", example = "EUR", required = true)
    private String sourceCurrency;

   // @Schema(description = "Code de la devise cible (ex: USD)", example = "USD", required = true)
    private String targetCurrency;

   // @Schema(description = "Montant à convertir", example = "100.50", required = true)
    private BigDecimal amount;

}
