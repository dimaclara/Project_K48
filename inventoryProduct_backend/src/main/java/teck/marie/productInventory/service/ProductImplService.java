package teck.marie.productInventory.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import teck.marie.productInventory.exception.ResourceNotFoundException;
import teck.marie.productInventory.exception.alreadyProductAndPriceAndStock;
import teck.marie.productInventory.model.Product;
import teck.marie.productInventory.model.dto.LowStockAlertResponse;
import teck.marie.productInventory.model.dto.ProductRequestDto;
import teck.marie.productInventory.model.dto.ProductResponseDto;
import teck.marie.productInventory.repository.ProductRepository;
import teck.marie.productInventory.service.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductImplService {
   private final ProductRepository productRepository;

   private final ProductMapper productMapper;
   public ProductImplService(ProductRepository productRepository, ProductMapper productMapper) {
       this.productRepository = productRepository;
       this.productMapper = productMapper;
   }




   // retrieve the products
   public List<ProductRequestDto> getAllProducts() {
       return  productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
   }


    // save the product in repository
    public Product save(Product product) {

        if (productRepository.existsByNameProductAndPriceAndStock(product.getNameProduct(),product.getPrice(),product.getStock())) {
            throw new alreadyProductAndPriceAndStock("Product already exists with the same name, price, and stock");
        }
        return productRepository.save(product);
    }


    // update the repository
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
       // verify if the id exist
        Product existingProduct =productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("product not find with id"+ id));

            //update the attribut

        existingProduct.setNameProduct(productRequestDto.getNameProduct());
        existingProduct.setPrice(productRequestDto.getPrice());
        existingProduct.setStock(productRequestDto.getStock());

        // save in BD

        Product updatedProduct = productRepository.save(existingProduct);

        // Convert the RESPONSE IN dto

        return productMapper.toResponseDto(updatedProduct);
    }

    // method to delete product

    public ProductResponseDto deleteProduct(Long id) {
       // verify if the Id existing
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("product not find with id" +  id));

        productRepository.delete(existingProduct);
        return productMapper.toResponseDto(existingProduct);

    }

    // method to Alert when product is less than 5
    public LowStockAlertResponse getLowStockAlert() {
       List<Product> lowStockProducts = productRepository.findByStockLessThan(5);
       List<ProductResponseDto> productResponseDtos = lowStockProducts.stream()
               .map(productMapper::toResponseDto)
               .collect(Collectors.toList());

        String message = productResponseDtos.isEmpty()
                ? "No products in stock."
                : "Alert: some products have less than 5 left in stock.";
        return new LowStockAlertResponse(message, productResponseDtos);

    }
}
