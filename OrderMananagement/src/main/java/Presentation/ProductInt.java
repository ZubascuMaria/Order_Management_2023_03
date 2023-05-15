package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aceasta este clasa principala din interfata product
 * Din aceasta interfata putem accesa, modifica, sterge sau insera date sau produse
 */
public class ProductInt extends JFrame implements ActionListener{
    JFrame frame;
    JButton view,add,delete,edit;
    public ProductInt()
    {
        frame = new JFrame("Product");
        frame.setBounds(400, 150, 700, 400);
        frame.getContentPane().setBackground(new Color(179, 210, 242));
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        view= new JButton("view products");
        view.setBounds(130, 90, 180, 60);
        view.setFont(new Font("T", Font.ITALIC, 22));
        view.setBackground(new Color(223, 231, 239));
        view.setForeground(Color.BLACK);
        frame.add(view);
        view.addActionListener(this);

        add= new JButton("add product");
        add.setBounds(380, 90, 180, 60);
        add.setFont(new Font("T", Font.ITALIC, 22));
        add.setBackground(new Color(223, 231, 239));
        add.setForeground(Color.BLACK);
        frame.add(add);
        add.addActionListener(this);

        delete= new JButton("delete product");
        delete.setBounds(130, 200, 190, 60);
        delete.setFont(new Font("T", Font.ITALIC, 22));
        delete.setBackground(new Color(223, 231, 239));
        delete.setForeground(Color.BLACK);
        frame.add(delete);
        delete.addActionListener(this);

        edit= new JButton("edit product");
        edit.setBounds(380, 200, 180, 60);
        edit.setFont(new Font("T", Font.ITALIC, 22));
        edit.setBackground(new Color(223, 231, 239));
        edit.setForeground(Color.BLACK);
        frame.add(edit);
        edit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view)
        {
            new ViewProducts();
        }else if(e.getSource()==add)
        {
            new ProductAdd();
        } else if (e.getSource()==delete)
        {
            new ProductDelete();
        }else if(e.getSource()==edit)
        {
            new ProductEdit();
        }

    }

}

