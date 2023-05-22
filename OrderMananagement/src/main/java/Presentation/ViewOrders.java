package Presentation;

import javax.swing.*;

public class ViewOrders extends JFrame {
    public ViewOrders()
    {
        JTable table=View.viewOrder();
        JScrollPane scrollPane=new JScrollPane(table);
        this.add(scrollPane);
        this.setTitle("All Orders");
        this.setBounds(400, 150, 730, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
