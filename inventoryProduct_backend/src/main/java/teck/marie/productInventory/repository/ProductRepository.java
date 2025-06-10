package teck.marie.productInventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teck.marie.productInventory.model.Product;
import teck.marie.productInventory.model.dto.ProductRequestDto;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByNameProductAndPriceAndStock(String nameProduct, double price, int stock);


    List<Product> findByStockLessThan(int i);
}
