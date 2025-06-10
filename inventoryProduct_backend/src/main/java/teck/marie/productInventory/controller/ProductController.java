package teck.marie.productInventory.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teck.marie.productInventory.model.Product;
import teck.marie.productInventory.model.dto.LowStockAlertResponse;
import teck.marie.productInventory.model.dto.ProductRequestDto;
import teck.marie.productInventory.model.dto.ProductResponseDto;
import teck.marie.productInventory.service.ProductImplService;
import teck.marie.productInventory.service.mapper.ProductMapper;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@Tag(name = "API RESTful (conforme)", description = "API allow to manage the products stock ")
public class ProductController {
    // injection of dependencies
   private final ProductImplService productImplService;
   private final ProductMapper productMapper;
   public ProductController(ProductImplService productImplService, ProductMapper productMapper) {
       this.productImplService = productImplService;
       this.productMapper = productMapper;
   }



    // Method to create product
    @Operation(summary = "create a new  product  in stock", description = "use the POST ")
    @PostMapping(path = "/create")
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto dto) {
        // Convertir DTO -> Entity

            Product product = productMapper.toEntity(dto);
            // serve in data  base
            //System.out.println(product);
            Product savedProduct = productImplService.save(product);

            // Convertir Entity -> DTO pour la réponse
            return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toResponseDto(savedProduct));


    }

    // get all product
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/product")
    @Operation(summary = "retrieve all the products ", description = "use GET with URI and return the List of all products")
    public List<ProductRequestDto> getAllProducts() {
       return productImplService.getAllProducts();
    }


    // update  the products by price or stock

    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "completely replace a product", description = "use PUT to update all the line")
    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productImplService.updateProduct(id, productRequestDto));

    }

    // delete product
    @Operation(summary = "delete a product ", description = "use Delete with a parameter in URL")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable Long id) {
       return ResponseEntity.ok(productImplService.deleteProduct(id));
    }


    //
    @GetMapping(path = "lowstock")
    public ResponseEntity<LowStockAlertResponse> getLowStockAlert() {
       return ResponseEntity.ok(productImplService.getLowStockAlert());
    }




}
