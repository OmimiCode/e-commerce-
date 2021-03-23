package com.pentazon.product;

import com.pentazon.exceptions.ProductException;

import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private ProductDB productRepoMock = new ProductDB();
    /**
     * search for product with specific id
     *
     * @param productId
     * @return product with specific product Id
     * @@throws productException
     */
    @Override
    public Product findProductById(String productId) throws ProductException {
        return productRepoMock.getProductById(productId);

    }

    @Override
    public boolean addProduct(Product product) throws ProductException {
        if (isValidProduct(product)){
            productRepoMock.getMockProducts().put(product.getProductId(),product);
            return true;
        }
        return false;
    }

    private boolean isValidProduct(Product product)throws ProductException{
                if (product==null){
            throw new ProductException("Cannot add a null product");
        }
        if (product.getName()==null || product.getName().equals("")){
            throw new ProductException("Product name is required");
        }
        if (product.getPrice()==null){
            throw new ProductException("Every product must have a price");
        }
        return true;

    }

    @Override
    public boolean removeProduct(Product product) throws ProductException {
        return false;
    }

    @Override
    public Map<String, Product> getAllProducts() {
        return productRepoMock.getMockProducts();
    }


}
