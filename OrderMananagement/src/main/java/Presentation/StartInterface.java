package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aceasta este clasa principala din care se initiaza interfata
 */
public class StartInterface extends JFrame implements ActionListener {
    JFrame frame;
    JButton client,product,order;
    public StartInterface() {
        frame = new JFrame("OrderManagement");
        frame.setBounds(400, 150, 730, 400);
        frame.getContentPane().setBackground(new Color(179, 210, 242));
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        client= new JButton("Client");
        client.setBounds(60, 120, 170, 70);
        client.setFont(new Font("T", Font.ITALIC, 22));
        client.setBackground(new Color(223, 231, 239));
        client.setForeground(Color.BLACK);
        frame.add(client);
        client.addActionListener(this);

        product= new JButton("Product");
        product.setBounds(280, 120, 170, 70);
        product.setFont(new Font("T", Font.ITALIC, 22));
        product.setBackground(new Color(223, 231, 239));
        product.setForeground(Color.BLACK);
        frame.add(product);
        product.addActionListener(this);

        order= new JButton("Order");
        order.setBounds(500, 120, 170, 70);
        order.setFont(new Font("T", Font.ITALIC, 22));
        order.setBackground(new Color(223, 231, 239));
        order.setForeground(Color.BLACK);
        frame.add(order);
        order.addActionListener(this);
    }
    public static void main(String[]args)
    {
        new StartInterface();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==client)
        {
            new ClientInt();
        }
        else if(e.getSource()==product)
        {
            new ProductInt();
        }
        else if(e.getSource()==order)
        {
            new OrdersInt();
        }

    }
}
