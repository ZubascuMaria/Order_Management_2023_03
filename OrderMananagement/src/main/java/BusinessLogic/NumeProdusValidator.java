package BusinessLogic;

import Model.Product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumeProdusValidator implements Validator<Product> {
    @Override
    public void validate(Product product) {
        Pattern ptr=Pattern.compile("^[a-zA-Z]+$");
        Matcher matc=ptr.matcher(product.getNumeProdus());
        if(!matc.matches())
            throw new IllegalArgumentException("Invalid product's name!\n");
    }
}
