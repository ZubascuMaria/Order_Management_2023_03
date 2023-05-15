package Presentation;

import BusinessLogic.ClientBLL;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientAdd extends JFrame implements ActionListener {
    JFrame frame;
    JTextField numef,adresa,contact;
    JButton add;
    JLabel errLabel;
    public ClientAdd()
    {
        frame = new JFrame("Add Client");
        frame.setBounds(500, 130, 370, 580);
        frame.getContentPane().setBackground(new Color(179, 210, 242));
        frame.setLayout(null);

////////////////////nume
        JLabel nume=new JLabel("Nume si prenume:");
        nume.setBounds(50,30,200,50);
        nume.setFont(new Font("A",Font.ITALIC,22));
        frame.add(nume);

        numef=new JTextField();
        numef.setBounds(50,80,250,40);
        numef.setFont(new Font("A",Font.ITALIC,20));
        numef.setVisible(true);
        frame.add(numef);
        numef.addActionListener(this);

///////////////////adresa
        JLabel adr=new JLabel("Adresa:");
        adr.setBounds(50,130,200,50);
        adr.setFont(new Font("A",Font.ITALIC,22));
        frame.add(adr);

        adresa=new JTextField();
        adresa.setBounds(50,180,250,40);
        adresa.setFont(new Font("A",Font.ITALIC,20));
        adresa.setVisible(true);
        frame.add(adresa);
        adresa.addActionListener(this);

/////////////////contact
        JLabel con=new JLabel("Contact:");
        con.setBounds(50,230,200,50);
        con.setFont(new Font("A",Font.ITALIC,22));
        frame.add(con);

        contact=new JTextField();
        contact.setBounds(50,280,250,40);
        contact.setFont(new Font("A",Font.ITALIC,20));
        contact.setVisible(true);
        frame.add(contact);
        contact.addActionListener(this);

/////////////buton
        add= new JButton("ADD");
        add.setBounds(90, 370, 150, 50);
        add.setFont(new Font("T", Font.ITALIC, 22));
        add.setBackground(new Color(223, 231, 239));
        add.setForeground(Color.BLACK);
        frame.add(add);
        add.addActionListener(this);

///////////////errLabel
        errLabel=new JLabel("...");
        errLabel.setBounds(30,420,200,50);
        errLabel.setFont(new Font("A",Font.ITALIC,22));
        frame.add(errLabel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==add)
        {
            try{
                String nume=numef.getText();
                String adrr=adresa.getText();
                String cont=contact.getText();
                Client client =new Client(nume,adrr,cont);
                ClientBLL clientBLL=new ClientBLL();
                clientBLL.insertClientBll(client);
                errLabel.setText("SUCCES!");

            }catch (Exception s)
            {
                errLabel.setText("Error: Wrong Data!");
                //numef.setText("");
               // adresa.setText("");
               // contact.setText("");
            }
        }

    }
}
