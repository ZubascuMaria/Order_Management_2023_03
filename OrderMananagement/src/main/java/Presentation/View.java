package Presentation;

import Connection.ReflectionClass;
import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Client;
import Model.Orders;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class View {
    /**
     * @param listaClienti
     * @return
     * Aceasta metoda creeaza un tabel care contine toti clientii din baza de date
     */
    public JTable viewClient(ArrayList<Client> listaClienti)
    {
        DefaultTableModel modelc=new DefaultTableModel();
        modelc.addColumn("ID");
        modelc.addColumn("Nume");
        modelc.addColumn("Adresa");
        modelc.addColumn("Contact");
        for(Client client:listaClienti)
        {
            Object[]values=new Object[4];
            ReflectionClass.retrieveProperties(client);
            values[0]=client.getCID();
            values[1]=client.getNume();
            values[2]=client.getAdresa();
            values[3]=client.getContact();
            modelc.addRow(values);
        }
        JTable table=new JTable(modelc);
        return table;
    }

    /**
     *
     * @param listaProduse
     * @return
     * Aceasta metoda returneaza un tabel care contine toate produsele din baza de date
     */
    public JTable viewProduct(ArrayList<Product> listaProduse)
    {
        DefaultTableModel modelc=new DefaultTableModel();
        modelc.addColumn("ID");
        modelc.addColumn("Nume Produs");
        modelc.addColumn("Pret");
        modelc.addColumn("Stoc");
        for(Product product:listaProduse)
        {
            Object[]values=new Object[4];
            ReflectionClass.retrieveProperties(product);
            values[0]=product.getPID();
            values[1]=product.getNumeProdus();
            values[2]=product.getPret();
            values[3]=product.getStoc();
            modelc.addRow(values);
        }
        JTable table=new JTable(modelc);
        return table;
    }

    /**
     *
     * @param listaOrd
     * @return
     * Aceasta metoda returneaza un tabel care contine toate comenzile din baza de date
     */
    public JTable viewOrder(ArrayList<Orders> listaOrd)
    {
        DefaultTableModel modelc=new DefaultTableModel();
        modelc.addColumn("ID");
        modelc.addColumn("Cantitate");
        modelc.addColumn("Nume client");
        modelc.addColumn("Nume Produs");
        for(Orders order:listaOrd)
        {
            Object[]values=new Object[4];
            ReflectionClass.retrieveProperties(order);
            values[0]=order.getOID();
            values[1]=order.getCantitate();
            Product p= ProductDAO.findProductById(order.getPID());
            Client c= ClientDAO.findClientById(order.getCID());
            values[2]=c.getNume();
            values[3]=p.getNumeProdus();
            modelc.addRow(values);
        }
        JTable table=new JTable(modelc);
        return table;
    }
}
