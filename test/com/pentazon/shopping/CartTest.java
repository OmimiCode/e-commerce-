package com.pentazon.shopping;

import com.pentazon.product.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    Cart cart;


    @BeforeEach
    void setUp() {
        cart = new Cart();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void weCanAddToCart(){
        assertTrue(cart.getCartItems().isEmpty());
        Product plantainChips = new Product("Adunni chips","Savoury plantain chips", BigDecimal.valueOf(100));
        plantainChips.setProductId("AD001");
        cart.addToCart(plantainChips);
        assertFalse(cart.getCartItems().isEmpty());
    }

    @Test
    void addToCart(){
        assertTrue(cart.getCartItems().isEmpty());
        Product plantainChips = new Product("Adunni chips","Savoury plantain chips", BigDecimal.valueOf(100));
        plantainChips.setProductId("AD001");
        cart.addToCart(plantainChips);
        assertFalse(cart.getCartItems().isEmpty());
        assertEquals(1,cart.getCartItems().size());

        boolean result = cart.removeFromCart(plantainChips);
        assertTrue(result);
        assertTrue(cart.getCartItems().isEmpty());
    }

    @Test
    void calculateTotal(){
        assertTrue(cart.getCartItems().isEmpty());
        Product plantainChips = new Product("Adunni chips","Savoury plantain chips", BigDecimal.valueOf(50));
        plantainChips.setProductId("AD001");
        cart.addToCart(plantainChips);
        Product shirt = new Product("Vintage shirt","Vintage Versace shirts",new BigDecimal(5000));
        shirt.setProductId("AD003");
        cart.addToCart(shirt);
        assertFalse(cart.getCartItems().isEmpty());
        assertEquals(2,cart.getCartItems().size());
        Item chipsItem = cart.getCartItems().get(plantainChips.getProductId());
        assertEquals(1,chipsItem.getQuantity());
        BigDecimal cartTotal = cart.calculateCartTotal();
        assertEquals(5050,cartTotal.intValue());
        cart.addToCart(plantainChips,9);
        assertEquals(10,chipsItem.getQuantity());
        cartTotal = cart.calculateCartTotal();
        assertEquals(5500,cartTotal.intValue());
    }



}