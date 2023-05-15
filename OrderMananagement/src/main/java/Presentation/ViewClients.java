package Presentation;

import DataAccess.ClientDAO;
import Model.Client;

import javax.swing.*;
import java.util.ArrayList;

public class ViewClients extends JFrame {
   public ViewClients()
    {
        ArrayList<Client> arr=ClientDAO.viewAllClients();
        View v=new View();
        JTable table=v.viewClient(arr);
        JScrollPane scrollPane=new JScrollPane(table);
        this.add(scrollPane);
        this.setTitle("All Clients");
        this.setBounds(400, 150, 730, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

}
