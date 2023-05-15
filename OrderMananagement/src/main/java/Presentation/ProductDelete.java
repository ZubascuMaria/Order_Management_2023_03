package Presentation;

import BusinessLogic.ProductBLL;
import DataAccess.ProductDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductDelete extends JFrame implements ActionListener {
    JFrame frame;
    JButton del;
    JComboBox cid;
    JLabel errLabel;

    public ProductDelete()
    {
        frame = new JFrame("Delete Product");
        frame.setBounds(500, 130, 480, 250);
        frame.getContentPane().setBackground(new Color(179, 210, 242));
        frame.setLayout(null);

////////////////////nume
        JLabel nume = new JLabel("Select product for delete:");
        nume.setBounds(50, 30, 300, 50);
        nume.setFont(new Font("A", Font.ITALIC, 22));
        frame.add(nume);

        cid=new JComboBox<>();
        cid.setModel(new DefaultComboBoxModel<String>(ProductDAO.getProducts().toArray(new String[0])));
        cid.setBounds(30,80,220,40);
        cid.setFont(new Font("A",Font.ITALIC,20));
        cid.setBackground(Color.WHITE);
        frame.add(cid);

        del = new JButton("DELETE");
        del.setBounds(280, 80, 150, 40);
        del.setFont(new Font("T", Font.ITALIC, 22));
        del.setBackground(new Color(223, 231, 239));
        del.setForeground(Color.BLACK);
        frame.add(del);
        del.addActionListener(this);

        errLabel = new JLabel("...");
        errLabel.setBounds(30, 120, 200, 50);
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
        if(e.getSource()==del)
        {
            try
            {
                String s=(String) cid.getSelectedItem();
                int productId=parse(s);
                ProductBLL productBLL=new ProductBLL();
                productBLL.deleteProductBll(productId);
                errLabel.setText("SUCCES!");

            }catch (Exception ex)
            {
                errLabel.setText("Error!");
            }
        }

    }

}
