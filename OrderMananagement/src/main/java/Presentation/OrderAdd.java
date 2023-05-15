package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Client;
import Model.Orders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderAdd extends JFrame implements ActionListener {

    JFrame frame;
    JTextField cantitate;
    JComboBox cid,pid;
    JButton add;
    JLabel errLabel;

    public OrderAdd()
    {
        frame = new JFrame("Add Order");
        frame.setBounds(500, 130, 370, 580);
        frame.getContentPane().setBackground(new Color(179, 210, 242));
        frame.setLayout(null);

////////////////////nume
        JLabel nume = new JLabel("ID si nume client:");
        nume.setBounds(50, 30, 200, 50);
        nume.setFont(new Font("A", Font.ITALIC, 22));
        frame.add(nume);

        cid=new JComboBox<>();
        cid.setModel(new DefaultComboBoxModel<String>(ClientDAO.getClients().toArray(new String[0])));
        cid.setBounds(50,80,200,40);
        cid.setFont(new Font("A",Font.ITALIC,20));
        cid.setBackground(Color.WHITE);
        frame.add(cid);

///////////////////produs
        JLabel adr = new JLabel("ID si nume produs:");
        adr.setBounds(50, 130, 200, 50);
        adr.setFont(new Font("A", Font.ITALIC, 22));
        frame.add(adr);

        pid=new JComboBox<>();
        pid.setModel(new DefaultComboBoxModel<String>(ProductDAO.getProducts().toArray(new String[0])));
        pid.setBounds(50,180,200,40);
        pid.setFont(new Font("A",Font.ITALIC,20));
        pid.setBackground(Color.WHITE);
        frame.add(pid);


/////////////////contact
        JLabel con = new JLabel("Cantitate:");
        con.setBounds(50, 230, 200, 50);
        con.setFont(new Font("A", Font.ITALIC, 22));
        frame.add(con);

        cantitate = new JTextField();
        cantitate.setBounds(50, 280, 200, 40);
        cantitate.setFont(new Font("A", Font.ITALIC, 20));
        cantitate.setVisible(true);
        frame.add(cantitate);
        cantitate.addActionListener(this);

/////////////buton
        add = new JButton("ADD");
        add.setBounds(90, 370, 150, 50);
        add.setFont(new Font("T", Font.ITALIC, 22));
        add.setBackground(new Color(223, 231, 239));
        add.setForeground(Color.BLACK);
        frame.add(add);
        add.addActionListener(this);

///////////////errLabel
        errLabel = new JLabel("...");
        errLabel.setBounds(30, 420, 200, 50);
        errLabel.setFont(new Font("A", Font.ITALIC, 22));
        frame.add(errLabel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }
    private int parse(String s)
    {
        Pattern ptr=Pattern.compile("(\\d+)");
        Matcher mat=ptr.matcher(s);
        if(mat.find())
        {
            int c=Integer.parseInt(mat.group(1));
            return c;
        }
        else return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            try {
                String client =(String) cid.getSelectedItem();
                String produs = (String) pid.getSelectedItem();
                Integer cant=Integer.parseInt(cantitate.getText());
               int clientId=parse(client);
               int prodId=parse(produs);
                Orders ord=new Orders(cant,clientId,prodId);
                OrderBLL orderBLL=new OrderBLL();
                orderBLL.insertOrderBll(ord);
                errLabel.setText("SUCCES!");

            } catch (Exception s) {
                errLabel.setText("Error: Wrong Data!");
            }
        }
    }

}



