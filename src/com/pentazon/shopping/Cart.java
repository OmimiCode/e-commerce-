package com.pentazon.shopping;

import com.pentazon.customers.Address;
import com.pentazon.exceptions.ProductException;
import com.pentazon.payments.PaymentCard;
import com.pentazon.product.Product;
import com.pentazon.product.ProductService;
import com.pentazon.product.ProductServiceImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

public class Cart {
    private Map<String, Item> cartItems;
    private ProductService productService;
    private Logger logger = Logger.getLogger(Cart.class.getName());
    private BigDecimal total = BigDecimal.ZERO;
    private PaymentCard paymentCard;
    private Address deliveryAddress;

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public PaymentCard getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(PaymentCard paymentCard) {
        this.paymentCard = paymentCard;
    }

    public Cart(){
        productService = new ProductServiceImpl();
        cartItems = new HashMap<>();
    }
    public  void addToCart(Product product){
        if (verifiedProduct(product)){
            Item item = cartItems.get(product.getProductId());
            if (item == null){
                item = new Item(product);
            }
            item.addItems(BigInteger.ONE.intValue());
            cartItems.put(product.getProductId(),item);
        }
    }
    public  void addToCart(Product product,int quantity){
        if (verifiedProduct(product)){
            Item item = cartItems.get(product.getProductId());
            if (item == null){
                item = new Item(product);
            }
            item.addItems(quantity);
            cartItems.put(product.getProductId(),item);
        }
    }

    private boolean verifiedProduct(Product product){
        boolean verified = false;
        if (product !=null){
            try{
                Product verifiedProduct = productService.findProductById(product.getProductId());
                verified = true;
            }catch (ProductException productException){
//                @todo add log message
//                logger.log();
            }
        }
        return verified;
    }

    public boolean removeFromCart(Product product){
        boolean removed = false;
        if (product != null){
            this.cartItems.remove(product.getProductId());
            removed = true;
        }
        return removed;
    }

    public Map<String, Item> getCartItems(){
        return cartItems;
    }
    public BigDecimal calculateCartTotal(){
        if (!cartItems.isEmpty()){
            this.total = BigDecimal.ZERO;
            Iterator<Item> cartItemIterator = cartItems.values().iterator();
            while (cartItemIterator.hasNext()){
                this.total = this.total.add(cartItemIterator.next().getTotal());

            }
        }
        return this.getTotal();
    }

    public BigDecimal getTotal() {
        return total;
    }


}
