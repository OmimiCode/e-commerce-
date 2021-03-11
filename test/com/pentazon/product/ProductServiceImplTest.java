package com.pentazon.product;

import com.pentazon.exceptions.ProductException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findProduct(){
        Product product = null;
        try {
            product = productService.findProductById("AD001");
        } catch (ProductException e) {
            e.printStackTrace();
        }
        assertNotNull(product);
    }

    @Test
    void findProductWithInvalidId(){
        assertThrows(ProductException.class,
                () -> productService.findProductById("DUD007"));
    }
}