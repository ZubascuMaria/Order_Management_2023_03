package BusinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import DataAccess.ClientDAO;
import Model.Client;

/**
 * Aceasta clasa contine metodele din ClientDAO in care parametrii sunt verificati
 * Verificarea se face cu ajutorul validatorilor
 * NameClientValidator verifica numele clientului - nu poate contine cifre si trebuie sa fie de tip nume si prenume, separate prin spatiu
 * ContactClientValidator verifica numarul de telefon - trebuie sa contina exact 10 cifre si sa inceapa cu 0
 */
public class ClientBLL {
    private List<Validator<Client>> validators;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new NameClientValidator());
        validators.add(new ContactClientValidator());
    }

    /**
     *
     * @param id
     * @return
     * Aceasta metoda cauta un client dupa id, dar mai intai verifica daca acest id exista, dupa care returneaza
     * clientul gasit
     * Cautarea clientului se realizeaza cu metoda findClientById din clasa ClientDAO
     */
    public Client findClientByIdBll(int id) {
        Client st = ClientDAO.findClientById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     *
     * @param client
     * @return
     * Aceasta metoda insereaza in baza de date un nou client, dat mai intai verifica daca atributele acestuia sunt corecte,
     * prin intermediul vlidatorilor, in caz afirmativ clientul va fi inserat
     * Inserarea se realizeaza cu ajutorul metodei insertClient din clasa ClientDAO
     */
    public int insertClientBll(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return ClientDAO.insertClient(client);
    }

    /**
     *
     * @param id
     * @return
     * Acesta metoda sterge un client cu un id dat, prima data se face verificarea id-ului, iar daca acesta exista se realizeaza stergerea
     * Stergerea se realizeaza cu metoda deleteClient din clasa ClientDAO
     */

    public int deleteClientBll(int id)
    {
        Client c=findClientByIdBll(id);
        int rez=1;
        if(c==null) {rez=0;throw new NoSuchElementException("The client with id =" + id +" was not found!");}
        return ClientDAO.deleteClient(id);
    }

    /**
     *
     * @param CID
     * @param newName
     * @param newAdress
     * @param newContact
     * @return
     * Aceasta metoda permite editarea informatiilor despre client, dar mai intai se verifica id-ul acestuia
     * Daca id-ul exista se creeaza un nou client cu noile atribute care se verifica prin validatori
     * Daca si atributele sunt corecte se realizeaza editarea prin metoda updateClient din clasa ClientDAO
     */
    public int updateClientValidatorBll(Integer CID, String newName, String newAdress, String newContact)
    {
        Client c=findClientByIdBll(CID);
        if(c!=null)
        {
            Client nc=new Client(CID,newName,newAdress,newContact);
            for(Validator<Client> v: validators)
                v.validate(nc);
        }
        return ClientDAO.updateClient(CID,newName,newAdress,newContact);
    }
}
