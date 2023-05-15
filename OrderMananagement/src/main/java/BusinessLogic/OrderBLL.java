package BusinessLogic;

import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Model.Client;
import Model.Orders;
import Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *  Aceasta clasa contine metodele din ClientDAO in care parametrii sunt verificati
 *  Verificarea se face cu ajutorul validatorilorâ
 *  CantitateOrderValidator verifica daca cantitatea este un numar pozitiv
 *  PidOrderValidaotr, CidOrderValidator verifica daca Id-ul clientului si Id-ul produsului exista
 */
public class OrderBLL {
    private List<Validator<Orders>> validators;

    public OrderBLL() {
        validators = new ArrayList<Validator<Orders>>();
        validators.add(new CantitateOrderValidator());
        validators.add(new PidOrderValidator());
        validators.add(new CidOrderValidator());
    }

    /**
     *
     * @param id
     * @return
     * Aceasta metoda cauta o comanda dupa id, dar mai intai verifica daca acest id exista, dupa care returneaza
     * comanda gasita
     * Cautarea comenzii se realizeaza cu metoda findOrderById din clasa OrderDAO
     */
    public Orders findOrderByIdBll(int id) {
        Orders st = OrderDAO.findOrderById(id);
        if (st == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     *
     * @param order
     * @return
     * Aceasta metoda insereaza in baza de date o noua comanda, dat mai intai verifica daca atributele acesteia sunt corecte,
     * prin intermediul vlidatorilor, in caz afirmativ comanda va fi inserata
     * Inserarea se realizeaza cu ajutorul metodei insertOrder din clasa OrderDAO
     */
    public int insertOrderBll(Orders order)
    {
        for (Validator<Orders> v : validators) {
            v.validate(order);
        }
        Product product= ProductDAO.findProductById(order.getPID());
        if(order.getCantitate()>product.getStoc())
            throw new NoSuchElementException("The order with id="+order.getOID()+" has a wrong quantity");
        else
        {
            product.setStoc(product.getStoc()-order.getCantitate());
            ProductDAO.updateProduct(product.getPID(),product.getNumeProdus(),product.getPret(),product.getStoc());
        }
        return OrderDAO.insertOrder(order);
    }

    /**
     *
     * @param OID
     * @return
     * Acesta metoda sterge un order cu un id dat, prima data se face verificarea id-ului, iar daca acesta exista se realizeaza stergerea
     * Stergerea se realizeaza cu metoda deleteOrder din clasa OrderDAO
     */
    public int deleteOrderBll(int OID)
    {
        Orders ord=findOrderByIdBll(OID);
        int rez=1;
       // int rez=OrderDAO.deleteOrder(OID);
        if(ord==null) {rez=0;throw new NoSuchElementException("The order with id =" + OID +" was not found!");}
        return rez;
    }

    /**
     *
     * @param OID
     * @param newCant
     * @param newCID
     * @param newPID
     * @return
     * Aceasta metoda permite editarea informatiilor despre o comanda, dar mai intai se verifica id-ul acesteia
     * Daca id-ul exista se creeaza o noua comanda cu noile atribute care se verifica prin validatori
     * Daca si atributele sunt corecte se realizeaza editarea prin metoda updateOrder din clasa OrderDAO
     */
    public int updateOrderBll(Integer OID, Integer newCant, Integer newCID, Integer newPID)
    {
        Orders order=findOrderByIdBll(OID);
        ProductBLL bp=new ProductBLL();
        Product p=bp.findProductByIdBll(newPID);
        ClientBLL bc=new ClientBLL();
        Client c=bc.findClientByIdBll(newCID);
        if(order!=null && p!=null && c!=null)
        {
          Orders neword=new Orders( OID,newCant,newCID,newPID) ;
            for(Validator<Orders> v: validators)
                v.validate(neword);
        }
        return OrderDAO.updateOrder(OID,newCant,newCID,newPID);
    }

}
