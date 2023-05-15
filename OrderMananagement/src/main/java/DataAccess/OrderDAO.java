package DataAccess;

import Connection.ConnectionFactory;
import Model.Orders;

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
 * Aceste metode sunt folosite pentru a cauta,insera,modifica sau sterge comenzi
 */
public class OrderDAO {
    protected static final Logger LOGGER = Logger.getLogger(Orders.class.getName());
    private final static String findStatementString = "SELECT * FROM orders where OID = ?";
    private static final String insertStatementString = "INSERT INTO orders (cantitate,CID,PID)"
            + " VALUES (?,?,?)";
    private final static String deleteStatementString="DELETE FROM orders WHERE OID=?";
    private final static String updateStatementString="UPDATE orders "+
            "set cantitate=?, CID=?, PID=?"+
            "where OID=?";
    private final static String viewStatementString="select * from orders";

    /**
     *
     * @param orderId
     * @return
     * Aceasta metoda cauta o comanda in functie de ID-ul unic pe care il are
     * Se realizeaza conexiunea cu mysql dupa care in rs se stocheaza tupla care are orderId
     * Aceste tupla este gasita printr-un select (findStatementString)
     */
    public static Orders findOrderById(Integer orderId) {
        Orders orderRet=null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, orderId);
            rs = findStatement.executeQuery();
            rs.next();

            Integer cantitate= rs.getInt("cantitate");
            Integer CID = rs.getInt("CID");
            Integer PID = rs.getInt("PID");

            orderRet = new Orders(orderId,cantitate,CID,PID);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return orderRet;
    }

    /**
     *
     * @param order
     * @return
     * Aceasta metoda introduce o noua comanda in baza de date
     * Se realizeaza conexiunea cu mysql, dupa care se introduce comanda printr-un insert (insertStatementString)
     */
    public static int insertOrder(Orders order) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1,order.getCantitate());
            insertStatement.setInt(2, order.getCID());
            insertStatement.setInt(3, order.getPID());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     *
     * @param OID
     * @return
     * Aceasta metoda sterge o comanda din baza de date dupa PID-ul unic pe care il are
     * Se realizeaza conexiunea la baza de date, dupa care se face un delete (deleteStatementString)
     */
    public static int deleteOrder(Integer OID) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int deleted = 0;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1,OID);
            deleteStatement.executeUpdate();
            deleted=1;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return deleted;
    }

    /**
     *
     * @param OID
     * @param newCant
     * @param newCID
     * @param newPID
     * @return
     * Aceasta metoda permite editarea datelor despre o comanda
     * Se realizeaza conectarea la baza de date, dupa care se fac modificarile printr-un update (updateStatementString)
     */
    public static int updateOrder(Integer OID, Integer newCant, Integer newCID, Integer newPID)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updated=0;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1,newCant);
            updateStatement.setInt(2,newCID);
            updateStatement.setInt(3,newPID);
            updateStatement.setInt(4,OID);
            updateStatement.executeUpdate();
            updated=1;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:update " + e.getMessage());
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
     * Se realizeaza conexiunea la mysql, dupa care, prin intermediul unui select, rs va contine fiecare tupla,
     * iar fiecare atribut va fi stocat intr-un order care va fi adaugat intr-un ArrayList de orders
     */
    public static ArrayList<Orders> viewAllOrders()
    {
        ArrayList<Orders>orders=new ArrayList<Orders>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;

        try {
            findStatement = dbConnection.prepareStatement(viewStatementString);

            rs = findStatement.executeQuery();
            while(rs.next())
            {
                Orders order=new Orders(rs.getInt("OID"),
                        rs.getInt("cantitate"),
                        rs.getInt("CID"),
                        rs.getInt("PID"));
                orders.add(order);

            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:viewAllOrders " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return orders;
    }
}
