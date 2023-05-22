package Presentation;

import Connection.ReflectionClass;
import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class View {
    /**
     * @param
     * @return
     * Aceasta metoda creeaza un tabel care contine toti clientii din baza de date
     */
    public static JTable getTable(ArrayList<?> list){
        DefaultTableModel modelc=new DefaultTableModel();
        ArrayList<Object> lista = ReflectionClass.retrieveProperties(list.get(1));
        for(Object o: lista)
            modelc.addColumn(o);
        for (Object obj: list)
        {
            Object [] o=new Object[ReflectionClass.retrieveProperties(list.get(0)).size()];
            int k=0;
            for (Field field : obj.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                try {
                    o[k]=field.get(obj);
                    k++;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }
            modelc.addRow(o);
        }

       return new JTable(modelc);
    }
    public static JTable viewClient()
    {
        return  getTable(ClientDAO.getList());
    }

    /**
     *
     * @param
     * @return
     * Aceasta metoda returneaza un tabel care contine toate produsele din baza de date
     */
    public static JTable viewProduct()
    {
        return getTable(ProductDAO.getList());
    }

    /**
     *
     * @param
     * @return
     * Aceasta metoda returneaza un tabel care contine toate comenzile din baza de date
     */
    public static JTable viewOrder()
    {

        return getTable(OrderDAO.getList());
    }
}
