package Presentation;

import DataAccess.ProductDAO;
import Model.Product;

import javax.swing.*;
import java.util.ArrayList;

public class ViewProducts extends JFrame {
    public ViewProducts()
    {
        ArrayList<Product> arr= ProductDAO.viewAllProducts();
        View v=new View();
        JTable table=v.viewProduct(arr);
        JScrollPane scrollPane=new JScrollPane(table);
        this.add(scrollPane);
        this.setTitle("All Products");
        this.setBounds(400, 150, 730, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
