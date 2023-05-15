package BusinessLogic;

import DataAccess.ProductDAO;
import Model.Orders;
import Model.Product;

public class PidOrderValidator implements Validator<Orders>{
    @Override
    public void validate(Orders orders) {
        ProductBLL p=new ProductBLL();
        p.findProductByIdBll(orders.getPID());
    }
}
