package Presentation;

import javax.swing.*;

public class ViewClients extends JFrame {
   public ViewClients()
    {

        JTable table=View.viewClient();
        JScrollPane scrollPane=new JScrollPane(table);
        this.add(scrollPane);
        this.setTitle("All Clients");
        this.setBounds(400, 150, 730, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

}
