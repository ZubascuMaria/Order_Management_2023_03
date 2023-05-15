package BusinessLogic;

import Model.Client;
import Model.Orders;

public class CidOrderValidator implements Validator<Orders>{
    @Override
    public void validate(Orders orders) {
        ClientBLL c=new ClientBLL();
        c.findClientByIdBll(orders.getCID());
    }
}
