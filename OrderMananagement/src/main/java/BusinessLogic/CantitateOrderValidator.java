package BusinessLogic;

import Model.Orders;

public class CantitateOrderValidator implements Validator<Orders>{
    @Override
    public void validate(Orders orders) {
        if(orders.getCantitate()<0)
            throw new IllegalArgumentException("Invalid order's quantity!\n");
    }
}
