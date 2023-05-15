package BusinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import DataAccess.ProductDAO;
import Model.Product;

/**
 *  Aceasta clasa contine metodele din ProductDAO in care parametrii sunt verificati
 *  Verificarea se face cu ajutorul validatorilor
 *  NumeProdusValidator verifica numele produsului- trebuie sa contina doar litere si sa fie un singur cuvant
 *  PretProdusValidator, StocProdusValidator- trebuie sa fie doar numere intregi si pozitive
 */
public class ProductBLL {
    private List<Validator<Product>> validators;

    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
       validators.add(new NumeProdusValidator());
       validators.add(new PretProdusValidator());
       validators.add(new StocProdusValidator());
    }

    /**
     *
     * @param id
     * @return
     * Aceasta metoda cauta un produs dupa id, dar mai intai verifica daca acest id exista, dupa care returneaza
     * produsul gasit
     * Cautarea produsului se realizeaza cu metoda findProductById din clasa ProductDAO
     */
    public Product findProductByIdBll(int id) {
        Product st = ProductDAO.findProductById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     *
     * @param product
     * @return
     * Aceasta metoda insereaza in baza de date un nou produs, dat mai intai verifica daca atributele acestuia sunt corecte,
     * prin intermediul vlidatorilor, in caz afirmativ produsul va fi inserat
     * Inserarea se realizeaza cu ajutorul metodei insertProduct din clasa ProductDAO
     */
    public int insertProductBll(Product product)
    {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        return ProductDAO.insertProduct(product);
    }

    /**
     *
     * @param id
     * @return
     * Acesta metoda sterge un produs cu un id dat, prima data se face verificarea id-ului, iar daca acesta exista se realizeaza stergerea
     * Stergerea se realizeaza cu metoda deleteProduct din clasa ProductDAO
     */
    public int deleteProductBll(int id)
    {
        Product p=findProductByIdBll(id);
        if(p==null) { throw new NoSuchElementException("The product with id =" + id +" was not found!"); }
        return ProductDAO.deleteProduct(id);
    }

    /**
     *
     * @param PID
     * @param newName
     * @param newPret
     * @param newStoc
     * @return
     * Aceasta metoda permite editarea informatiilor despre produs, dar mai intai se verifica id-ul acestuia
     * Daca id-ul exista se creeaza un nou produs cu noile atribute care se verifica prin validatori
     * Daca si atributele sunt corecte se realizeaza editarea prin metoda updateProduct din clasa ProductDAO
     */
    public int updateProductBll(Integer PID, String newName, Integer newPret, Integer newStoc)
    {
        Product p=findProductByIdBll(PID);
        if(p!=null)
        {
            Product np=new Product(PID,newName,newPret,newStoc);
            for(Validator<Product> v: validators)
                v.validate(np);
        }
        return ProductDAO.updateProduct(PID,newName,newPret,newStoc);
    }
}
