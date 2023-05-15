package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Aceasta este clasa principala din interfata order
 * Din aceasta interfata putem accesa sau insera date si comenzi noi
 */
public class OrdersInt extends JFrame implements ActionListener{
    JFrame frame;
    JButton view,add,delete,edit;
    public OrdersInt()
    {
        frame = new JFrame("Order");
        frame.setBounds(400, 150, 700, 200);
        frame.getContentPane().setBackground(new Color(179, 210, 242));
        frame.setLayout(null);

        view= new JButton("view orders");
        view.setBounds(130, 50, 150, 60);
        view.setFont(new Font("T", Font.ITALIC, 22));
        view.setBackground(new Color(223, 231, 239));
        view.setForeground(Color.BLACK);
        frame.add(view);
        view.addActionListener(this);

        add= new JButton("add order");
        add.setBounds(380, 50, 150, 60);
        add.setFont(new Font("T", Font.ITALIC, 22));
        add.setBackground(new Color(223, 231, 239));
        add.setForeground(Color.BLACK);
        frame.add(add);
        add.addActionListener(this);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view)
        {
            new ViewOrders();
        }else if(e.getSource()==add)
        {
            new OrderAdd();
        }

    }
}
