package com.pentazon.product;


import com.pentazon.exceptions.ProductException;

import java.util.Map;

/**
 * Provides services for dealing with products in pentazon
 * */

public interface ProductService {
    /**
     * search for product with specific id
     *
     * @param productId
     * @return product with specific product Id
     */

    Product findProductById(String productId) throws ProductException;



    public boolean addProduct(Product product) throws  ProductException;

    /**
     * product should no longer be offered to buyers
     * @param product
     * @return
     * */

    public boolean removeProduct(Product product) throws ProductException;

    public Map<String,Product> getAllProducts();

}