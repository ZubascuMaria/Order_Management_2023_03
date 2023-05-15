package Presentation;

import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import Model.Client;
import Model.Orders;

import javax.swing.*;
import java.util.ArrayList;

public class ViewOrders extends JFrame {
    public ViewOrders()
    {
        ArrayList<Orders> arr= OrderDAO.viewAllOrders();
        View v=new View();
        JTable table=v.viewOrder(arr);
        JScrollPane scrollPane=new JScrollPane(table);
        this.add(scrollPane);
        this.setTitle("All Orders");
        this.setBounds(400, 150, 730, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
