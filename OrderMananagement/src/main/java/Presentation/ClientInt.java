package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aceasta este clasa principala din interfata client
 * Din aceasta interfata putem accesa, modifica, sterge sau insera date sau clienti
 */
public class ClientInt extends JFrame implements ActionListener {
    JFrame frame;
    JButton view,add,delete,edit;
    public ClientInt()
    {
        frame = new JFrame("Client");
        frame.setBounds(400, 150, 700, 400);
        frame.getContentPane().setBackground(new Color(179, 210, 242));
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        view= new JButton("view clients");
        view.setBounds(130, 90, 150, 60);
        view.setFont(new Font("T", Font.ITALIC, 22));
        view.setBackground(new Color(223, 231, 239));
        view.setForeground(Color.BLACK);
        frame.add(view);
        view.addActionListener(this);

        add= new JButton("add client");
        add.setBounds(380, 90, 150, 60);
        add.setFont(new Font("T", Font.ITALIC, 22));
        add.setBackground(new Color(223, 231, 239));
        add.setForeground(Color.BLACK);
        frame.add(add);
        add.addActionListener(this);

        delete= new JButton("delete client");
        delete.setBounds(130, 200, 160, 60);
        delete.setFont(new Font("T", Font.ITALIC, 22));
        delete.setBackground(new Color(223, 231, 239));
        delete.setForeground(Color.BLACK);
        frame.add(delete);
        delete.addActionListener(this);

        edit= new JButton("edit client");
        edit.setBounds(380, 200, 150, 60);
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
            new ViewClients();
        }else if(e.getSource()==add)
        {
            new ClientAdd();
        }
        else if(e.getSource()==delete)
        {
            new ClientDelete();
        }else if(e.getSource()==edit)
        {
            new ClientEdit();
        }

    }

}
