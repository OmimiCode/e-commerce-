package com.pentazon.product;

import com.pentazon.exceptions.ProductException;

/**
 * Provides services for dealing with products in Pentazon
 */
public interface ProductService {
    /**
     * search for product with the specified id
     * @param productId
     * @return
     * @throws ProductException
     */
    public Product findProductById(String productId) throws ProductException;
}