package tech.marie.currency_conversion.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import tech.marie.currency_conversion.model.ConversionRequest;
import tech.marie.currency_conversion.model.ConversionResponse;
import tech.marie.currency_conversion.service.ConversionService;

@RestController
@Slf4j
@RequestMapping("/api/v1/currency")
@Tag(name = "Currency Converter", description = "API pour la conversion de devises")
public class CurrencyConverterController {

    private final ConversionService conversionService;

    public CurrencyConverterController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping("/convert")
    @Operation(
            summary = "Convertir un montant d'une devise à une autre",
            description = "Endpoint permettant de convertir un montant entre deux devises en utilisant les taux de change actuels")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conversion réussie",
                    content = @Content(schema = @Schema(implementation = ConversionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide ou devise non supportée"),
            @ApiResponse(responseCode = "500", description = "Erreur lors de la communication avec l'API externe")
    })
    public Mono<ResponseEntity<ConversionResponse>> convertCurrency(@RequestBody ConversionRequest conversionRequest) {
        log.info("Received conversion request: {}", conversionRequest);
        return conversionService.convertCurrent(conversionRequest)
                .map(ResponseEntity::ok)
                .onErrorResume(ex -> {
                    log.error("Error during currency conversion", ex);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }
}
