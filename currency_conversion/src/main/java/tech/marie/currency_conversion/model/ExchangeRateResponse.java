package tech.marie.currency_conversion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class ExchangeRateResponse {
    private boolean success;
    private long timestamp;
    private String base;
    private String date;
    @JsonProperty("conversion_rates")
    private Map<String, Double> rates;
}
