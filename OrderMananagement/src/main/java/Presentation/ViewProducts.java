package Presentation;

import javax.swing.*;

public class ViewProducts extends JFrame {
    public ViewProducts()
    {

        JTable table=View.viewProduct();
        JScrollPane scrollPane=new JScrollPane(table);
        this.add(scrollPane);
        this.setTitle("All Products");
        this.setBounds(400, 150, 730, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
