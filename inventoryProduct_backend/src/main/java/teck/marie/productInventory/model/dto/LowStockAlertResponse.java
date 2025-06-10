package teck.marie.productInventory.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class LowStockAlertResponse {
    private String message;
    private List<ProductResponseDto> products;
}
