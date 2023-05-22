package DataAccess;
import Connection.ConnectionFactory;
import Model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Aceasta clasa contine metode care fac legatura cu baza de date
 * Aceste metode sunt folosite pentru a cauta,insera,modifica sau sterge clienti
 */
public class ClientDAO {
    protected static final Logger LOGGER = Logger.getLogger(Client.class.getName());
    private static final String insertStatementString = "INSERT INTO clients (nume,adresa,contact)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM clients where CID = ?";
    private final static String deleteStatementString="DELETE FROM clients WHERE CID=?";
    private final static String updateStatementString="UPDATE clients "+
                                                        "set nume=?, adresa=?, contact=?"+
                                                        "where CID=?";
    private final static String viewStatementString="select * from clients";

    /**
     *
     * @param ClientId
     * @return clientRet
     * Aceasta metoda cauta un client in functie de ID-ul unic pe care il are
     * Se realizeaza conexiunea cu mysql dupa care in rs se stocheaza tupla care are clientId
     * Aceste tupla este gasita printr-un select (findStatementString)
     */
    public static Client findClientById(int ClientId) {
        Client clientRet = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, ClientId);
            rs = findStatement.executeQuery();
            rs.next();

            String nume = rs.getString("nume");
            String adresa = rs.getString("adresa");
            String contact = rs.getString("contact");

            clientRet = new Client(ClientId,nume,adresa,contact);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return clientRet;
    }

    /**
     *
     * @param client
     * @return
     * Aceasta metoda introduce un client nou in baza de date
     * Se realizeaza conexiunea cu mysql, dupa care se introduce clientul printr-un insert (insertStatementString)
     */
    public static int insertClient(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, client.getNume());
            insertStatement.setString(2, client.getAdresa());
            insertStatement.setString(3, client.getContact());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     *
     * @param CID
     * @return
     * Aceasta metoda sterge un client dupa ID-ul acestuia
     * Se realizeaza conexiunea dupa care se sterge clientul printr-un delete (deleteStatementString)
     */
    public static int deleteClient(Integer CID) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int deleted = 0;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1,CID);
            deleteStatement.executeUpdate();
            deleted=1;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return deleted;
    }

    /**
     *
     * @param CID
     * @param newName
     * @param newAdress
     * @param newContact
     * @return
     * Aceasta metoda permite editarea datelor despre un client
     * Se realizeaza conectarea la baza de date, dupa care se fac modificarile printr-un update (updateStatementString)
     */
    public static int updateClient(Integer CID, String newName, String newAdress, String newContact)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updated=0;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1,newName);
            updateStatement.setString(2,newAdress);
            updateStatement.setString(3,newContact);
            updateStatement.setInt(4,CID);
            updateStatement.executeUpdate();
            updated=1;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updated;
    }

    /**
     *
     * @return
     * Aceasta metoda permite vizualizarea intregului tabel din baza de date
     * Se realizeaza conexiunea la mysql, dupa care, prin intermediul unui select, rs va contine fiecare tupla, iar fiecare atribut va fi stocat intr-un client care va fi adaugat intr-un ArrayList de clienti
     */
    public static ArrayList<Client> viewAllClients()
    {
        ArrayList<Client>clients=new ArrayList<Client>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;

        try {
            findStatement = dbConnection.prepareStatement(viewStatementString);

            rs = findStatement.executeQuery();
            while(rs.next())
            {
                Client client=new Client(rs.getInt("CID"),
                        rs.getString("nume"),
                        rs.getString("adresa"),
                        rs.getString("contact"));
                clients.add(client);

            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:viewAllClients " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return clients;
    }
    public static ArrayList<String> getClients()
    {
        ArrayList<String> cli=new ArrayList<String>();
        ArrayList<Client> clientArrayList=viewAllClients();
        for(Client c: clientArrayList)
        {
            cli.add(c.getCID()+" "+c.getNume());
        }
        return cli;
    }

    public static ArrayList<String> getClientsinfo()
    {
        ArrayList<String> cli=new ArrayList<String>();
        ArrayList<Client> clientArrayList=viewAllClients();
        for(Client c: clientArrayList)
        {
            cli.add(c.getCID()+", "+c.getNume()+", "+c.getAdresa()+", "+c.getContact());
        }
        return cli;
    }
    public static ArrayList<Client> getList()
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        ArrayList<Client> arr=new ArrayList<>();
            try {
                insertStatement = dbConnection.prepareStatement(viewStatementString, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = null;
                rs=insertStatement.executeQuery();
                 while(rs.next())
                {
                    int CID=rs.getInt("CID");
                    String nume=rs.getString("nume");
                    String adresa=rs.getString("adresa");
                    String contact=rs.getString("contact");
                    arr.add(new Client(CID,nume,adresa,contact));
                }

            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "ClientDAO:getList " + e.getMessage());
            } finally {
                ConnectionFactory.close(insertStatement);
                ConnectionFactory.close(dbConnection);
            }
            return arr;
    }


}
