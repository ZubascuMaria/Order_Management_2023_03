package BusinessLogic;

import Model.Product;

public class StocProdusValidator implements Validator<Product>{
    @Override
    public void validate(Product product) {
        if(product.getStoc()<0)
            throw new IllegalArgumentException("Invalid product's stoc!\n");
    }
}
