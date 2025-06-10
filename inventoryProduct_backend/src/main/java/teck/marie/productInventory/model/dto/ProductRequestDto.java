package teck.marie.productInventory.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDto {

    private Long id;

    @NotBlank(message = "name of product is mandatory")
    private String nameProduct;

    @Min(value = 0, message = "price must be positif")
    private double price;
    @Min(value = 0, message = "must not  be negatif")
    private int stock;


}
