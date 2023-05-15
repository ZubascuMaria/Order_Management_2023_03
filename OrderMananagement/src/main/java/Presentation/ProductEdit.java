package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;
import DataAccess.ProductDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductEdit extends JFrame implements ActionListener {
    JFrame frame;
    JButton edit;
    JComboBox cid;
    JTextField nume, pret, stoc;
    JLabel errLabel;
    public ProductEdit()
    {
        frame = new JFrame("Edit Product");
        frame.setBounds(400, 150, 700, 400);
        frame.getContentPane().setBackground(new Color(179, 210, 242));
        frame.setLayout(null);

        JLabel a = new JLabel("Select product for edit:");
        a.setBounds(50, 10, 250, 50);
        a.setFont(new Font("A", Font.ITALIC, 22));
        frame.add(a);

        cid=new JComboBox<>();
        cid.setModel(new DefaultComboBoxModel<String>(ProductDAO.getProductsinfo().toArray(new String[0])));
        cid.setBounds(30,60,450,40);
        cid.setFont(new Font("A",Font.ITALIC,20));
        cid.setBackground(Color.WHITE);
        frame.add(cid);

        edit = new JButton("EDIT");
        edit.setBounds(500, 60, 100, 40);
        edit.setFont(new Font("T", Font.ITALIC, 20));
        edit.setBackground(new Color(223, 231, 239));
        edit.setForeground(Color.BLACK);
        frame.add(edit);
        edit.addActionListener(this);

        JLabel num = new JLabel("Nume:");
        num.setBounds(50, 120, 250, 50);
        num.setFont(new Font("A", Font.ITALIC, 22));
        frame.add(num);
        nume=new JTextField();
        nume.setBounds(150,120,250,40);
        nume.setFont(new Font("A",Font.ITALIC,20));
        nume.setVisible(true);
        frame.add(nume);
        nume.addActionListener(this);

        JLabel num1 = new JLabel("Pret:");
        num1.setBounds(50, 170, 250, 50);
        num1.setFont(new Font("A", Font.ITALIC, 22));
        frame.add(num1);
        pret =new JTextField();
        pret.setBounds(150,170,250,40);
        pret.setFont(new Font("A",Font.ITALIC,20));
        pret.setVisible(true);
        frame.add(pret);
        pret.addActionListener(this);

        JLabel num2 = new JLabel("Stoc:");
        num2.setBounds(50, 220, 250, 50);
        num2.setFont(new Font("A", Font.ITALIC, 22));
        frame.add(num2);
        stoc =new JTextField();
        stoc.setBounds(150,220,250,40);
        stoc.setFont(new Font("A",Font.ITALIC,20));
        stoc.setVisible(true);
        frame.add(stoc);
        stoc.addActionListener(this);

        errLabel=new JLabel("...");
        errLabel.setBounds(50,270,200,50);
        errLabel.setFont(new Font("A",Font.ITALIC,22));
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
        if(e.getSource()==edit)
        {
            try
            {
                String c=(String) cid.getSelectedItem();
                int productId=parse(c);
                String newName=nume.getText();
                Integer newpret=Integer.parseInt(pret.getText());
                Integer newstoc=Integer.parseInt(stoc.getText());
                ProductBLL productBLL=new ProductBLL();
                productBLL.updateProductBll(productId,newName,newpret,newstoc);
                errLabel.setText("SUCCESS!");

            }catch (Exception es)
            {
                errLabel.setText("Wrong Data!");
            }
        }

    }

}
