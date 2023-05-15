package Presentation;

import BusinessLogic.ProductBLL;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductAdd extends JFrame implements ActionListener {
    JFrame frame;
    JTextField numef,pret,stoc;
    JButton add;
    JLabel errLabel;
    public ProductAdd()
    {
        frame = new JFrame("Add Product");
        frame.setBounds(500, 130, 370, 580);
        frame.getContentPane().setBackground(new Color(179, 210, 242));
        frame.setLayout(null);

////////////////////nume
        JLabel nume=new JLabel("Nume produs:");
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
        JLabel adr=new JLabel("Pret:");
        adr.setBounds(50,130,200,50);
        adr.setFont(new Font("A",Font.ITALIC,22));
        frame.add(adr);

        pret=new JTextField();
        pret.setBounds(50,180,250,40);
        pret.setFont(new Font("A",Font.ITALIC,20));
        pret.setVisible(true);
        frame.add(pret);
        pret.addActionListener(this);

/////////////////contact
        JLabel con=new JLabel("Stoc:");
        con.setBounds(50,230,200,50);
        con.setFont(new Font("A",Font.ITALIC,22));
        frame.add(con);

        stoc=new JTextField();
        stoc.setBounds(50,280,250,40);
        stoc.setFont(new Font("A",Font.ITALIC,20));
        stoc.setVisible(true);
        frame.add(stoc);
        stoc.addActionListener(this);

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
                Integer pr=Integer.parseInt(pret.getText());
                Integer st=Integer.parseInt(stoc.getText());
                Product product=new Product(nume,pr,st);
                ProductBLL productBLL=new ProductBLL();
                productBLL.insertProductBll(product);
                errLabel.setText("SUCCES!");

            }catch (Exception s)
            {
                errLabel.setText("Error: Wrong Data!");
            }
        }

    }

}
