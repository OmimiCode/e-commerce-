package com.pentazon.product;

import com.pentazon.exceptions.ProductException;
import com.pentazon.shopping.Cart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {
    Product product;
    ProductService productService;
    Cart cart;

    @BeforeEach
    void setUp() {
        product = new Product("testProduct", "a good product", BigDecimal.valueOf(10));
        product.setProductId("AD005");
        productService = new ProductServiceImpl();
        cart = new Cart();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findProduct() {
        Product product = null;
        try {
            product = productService.findProductById("AD001");
        } catch (ProductException productException) {
            productException.printStackTrace();
        }
        assertNotNull(product);
    }

    @Test
    void findProductByIdWithInvalidId() {
        assertThrows(ProductException.class, () -> productService.findProductById("AD008"));
    }

    @Test
    void addProduct() {
        Map<String, Product> initialProducts = productService.getAllProducts();
        assertTrue(initialProducts.size() == 3);
        try {
            boolean result = productService.addProduct(product);
            Map<String, Product> allProducts = productService.getAllProducts();
            assertTrue(result);
            assertTrue(allProducts.size() == 4);

        } catch (ProductException productException) {
            productException.printStackTrace();
        }
    }

}
