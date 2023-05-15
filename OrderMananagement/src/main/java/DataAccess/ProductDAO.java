package DataAccess;

import Connection.ConnectionFactory;

import Model.Product;

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
 * Aceste metode sunt folosite pentru a cauta,insera,modifica sau sterge produse
 */
public class ProductDAO {
    protected static final Logger LOGGER = Logger.getLogger(Product.class.getName());
    private final static String findStatementString = "SELECT * FROM product where PID = ?";
    private static final String insertStatementString = "INSERT INTO product (numeProdus,pret,stoc)"
                                                        + " VALUES (?,?,?)";
    private final static String deleteStatementString="DELETE FROM product WHERE PID=?";
    private final static String updateStatementString="UPDATE product "+
            "set numeProdus=?, pret=?, stoc=? "+
            "where PID=?";
    private final static String viewStatementString="select * from product";

    /**
     *
     * @param ProductId
     * @return
     * Aceasta metoda cauta un produs in functie de ID-ul unic pe care il are
     * Se realizeaza conexiunea cu mysql dupa care in rs se stocheaza tupla care are ProductId
     * Aceste tupla este gasita printr-un select (findStatementString)
     */
    public static Product findProductById(Integer ProductId) {
        Product productRet=null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, ProductId);
            rs = findStatement.executeQuery();
            rs.next();

            String nume = rs.getString("numeProdus");
            Integer pret = rs.getInt("pret");
            Integer stoc = rs.getInt("stoc");

           productRet = new Product(ProductId,nume,pret,stoc);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return productRet;
    }

    /**
     *
     * @param product
     * @return
     * Aceasta metoda introduce un produs nou in baza de date
     * Se realizeaza conexiunea cu mysql, dupa care se introduce produsul printr-un insert (insertStatementString)
     */
    public static int insertProduct(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getNumeProdus());
            insertStatement.setInt(2, product.getPret());
            insertStatement.setInt(3, product.getStoc());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     *
     * @param PID
     * @return
     * Aceasta metoda sterge un produs din baza de date dupa PID-ul unic pe care il are
     * Se realizeaza conexiunea la baza de date, dupa care se face un delete (deleteStatementString)
     */
    public static int deleteProduct(Integer PID) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int deleted = 0;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1,PID);
            deleteStatement.executeUpdate();
            deleted=1;

        } catch (SQLException e) {
            deleted=0;
            LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return deleted;
    }

    /**
     *
     * @param PID
     * @param newName
     * @param newPret
     * @param newStoc
     * @return
     * Aceasta metoda permite editarea datelor despre un produs
     * Se realizeaza conectarea la baza de date, dupa care se fac modificarile printr-un update (updateStatementString)
     */
    public static int updateProduct(Integer PID, String newName, Integer newPret, Integer newStoc)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updated=0;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1,newName);
            updateStatement.setInt(2,newPret);
            updateStatement.setInt(3,newStoc);
            updateStatement.setInt(4,PID);
            updateStatement.executeUpdate();
            updated=1;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updated;
    }

    /**
     *
     * @return
     *  Aceasta metoda permite vizualizarea intregului tabel din baza de date
     *  Se realizeaza conexiunea la mysql, dupa care, prin intermediul unui select, rs va contine fiecare tupla,
     *  iar fiecare atribut va fi stocat intr-un produs care va fi adaugat intr-un ArrayList de produse
     */
    public static ArrayList<Product> viewAllProducts()
    {
        ArrayList<Product>products=new ArrayList<Product>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;

        try {
            findStatement = dbConnection.prepareStatement(viewStatementString);

            rs = findStatement.executeQuery();
            while(rs.next())
            {
                Product product=new Product(rs.getInt("PID"),
                        rs.getString("numeProdus"),
                        rs.getInt("pret"),
                        rs.getInt("stoc"));
                products.add(product);

            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:viewAllProducts " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return products;
    }
    public static ArrayList<String> getProducts()
    {
        ArrayList<String> pro=new ArrayList<String>();
        ArrayList<Product> productsArrayList=viewAllProducts();
        for(Product c: productsArrayList)
        {
            pro.add(c.getPID()+" "+c.getNumeProdus()+" "+c.getStoc());
        }
        return pro;
    }

    public static ArrayList<String> getProductsinfo()
    {
        ArrayList<String> pro=new ArrayList<String>();
        ArrayList<Product> productsArrayList=viewAllProducts();
        for(Product c: productsArrayList)
        {
            pro.add(c.getPID()+", "+c.getNumeProdus()+", "+c.getPret()+", "+c.getStoc());
        }
        return pro;
    }


}
