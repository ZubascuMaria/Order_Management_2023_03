package BusinessLogic;

import Model.Product;

public class PretProdusValidator implements Validator<Product>{
    @Override
    public void validate(Product product) {
        if(product.getPret()<0)
            throw new IllegalArgumentException("Invalid product's price!\n");
    }
}
