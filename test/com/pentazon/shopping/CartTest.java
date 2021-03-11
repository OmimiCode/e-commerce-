package com.pentazon.shopping;

import com.pentazon.product.Product;
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

    @Test
    void addToCart(){
        assertTrue(cart.getItems().isEmpty());
        Product plantainChips = new Product("Adunni Chips", "Savoury plantain chips", new BigDecimal(50));
        plantainChips.setProductId("AD001");
        cart.addToCart(plantainChips);
        assertFalse(cart.getItems().isEmpty());
        assertEquals(1, cart.getItems().size());
    }

    @Test
    void removeFromCart(){
        assertTrue(cart.getItems().isEmpty());
        Product plantainChips = new Product("Adunni Chips", "Savoury plantain chips", new BigDecimal(50));
        plantainChips.setProductId("AD001");
        cart.addToCart(plantainChips);
        assertFalse(cart.getItems().isEmpty());
        assertEquals(1, cart.getItems().size());

        boolean result = cart.removeFromCart(plantainChips);
        assertTrue(result);
        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    void calculateTotal(){
        assertTrue(cart.getItems().isEmpty());
        Product plantainChips = new Product("Adunni Chips", "Savoury plantain chips", new BigDecimal(50));
        plantainChips.setProductId("AD001");
        cart.addToCart(plantainChips);
        Product shirt = new Product("Vintage Shirt", "Vintage Versace shirt", new BigDecimal(5000));
        shirt.setProductId("AD003");
        cart.addToCart(shirt);
        assertFalse(cart.getItems().isEmpty());
        assertEquals(2, cart.getItems().size());
        BigDecimal cartTotal = cart.calculateCartTotal();
        assertEquals(5050, cartTotal.intValue());
    }

    @Test
    void calculateTotalWithMultipleChips(){
        assertTrue(cart.getItems().isEmpty());
        Product plantainChips = new Product("Adunni Chips", "Savoury plantain chips", new BigDecimal(50));
        plantainChips.setProductId("AD001");
        cart.addToCart(plantainChips);
        Product shirt = new Product("Vintage Shirt", "Vintage Versace shirt", new BigDecimal(5000));
        shirt.setProductId("AD003");
        cart.addToCart(shirt);
        assertFalse(cart.getItems().isEmpty());
        assertEquals(2, cart.getItems().size());
        BigDecimal cartTotal = cart.calculateCartTotal();
        assertEquals(5050, cartTotal.intValue());

        CartItem chipsItem = cart.getItems().get(plantainChips.getProductId());
        assertEquals(1, chipsItem.getQuantity());

        CartItem shirtItem = cart.getItems().get(shirt.getProductId());
        assertEquals(1, shirtItem.getQuantity());

        cart.addToCart(plantainChips, 9);
        assertEquals(2, cart.getItems().size());
        chipsItem = cart.getItems().get(plantainChips.getProductId());
        assertEquals(10, chipsItem.getQuantity());
        cartTotal = cart.calculateCartTotal();
        assertEquals(5500, cartTotal.intValue());
    }

    @Test
    void calculateTotalWithMultipleChipsAndShirts(){
        assertTrue(cart.getItems().isEmpty());
        Product plantainChips = new Product("Adunni Chips", "Savoury plantain chips", new BigDecimal(50));
        plantainChips.setProductId("AD001");
        cart.addToCart(plantainChips);
        Product shirt = new Product("Vintage Shirt", "Vintage Versace shirt", new BigDecimal(5000));
        shirt.setProductId("AD003");
        cart.addToCart(shirt);
        assertFalse(cart.getItems().isEmpty());
        assertEquals(2, cart.getItems().size());
        BigDecimal cartTotal = cart.calculateCartTotal();
        assertEquals(5050, cartTotal.intValue());

        CartItem chipsItem = cart.getItems().get(plantainChips.getProductId());
        assertEquals(1, chipsItem.getQuantity());

        CartItem shirtItem = cart.getItems().get(shirt.getProductId());
        assertEquals(1, shirtItem.getQuantity());

        cart.addToCart(plantainChips, 9);
        assertEquals(2, cart.getItems().size());
        chipsItem = cart.getItems().get(plantainChips.getProductId());
        assertEquals(10, chipsItem.getQuantity());
        cartTotal = cart.calculateCartTotal();
        assertEquals(5500, cartTotal.intValue());

        cart.addToCart(shirt, 3);
        assertEquals(2, cart.getItems().size());
        shirtItem = cart.getItems().get(shirt.getProductId());
        assertEquals(4, shirtItem.getQuantity());
        cartTotal = cart.calculateCartTotal();
        assertEquals(20500, cartTotal.intValue());
    }
}