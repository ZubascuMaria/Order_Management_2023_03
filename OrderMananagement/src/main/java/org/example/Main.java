package org.example;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import DataAccess.ClientDAO;
import Model.Client;
import Model.Orders;
import Model.Product;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
    // int c= ClientDAO.insertClient(new Client("Ana Maria","Strada Baicu 14","0740022744"));
       //int s=ClientDAO.deleteClient(1);
        //int d=ClientDAO.updateClient(3,"Vasi Vasile","Baicu 20","0740022747");
        ClientBLL c=new ClientBLL();
       // c.insertClientBll(new Client("Maria Adrea","Calea Motilor 17","0743555417"));
       // c.updateClientValidatorBll(3,"Zubascu Ileana","Cluj 239","0786999551");
        //c.deleteClientBll(3);
        ProductBLL p=new ProductBLL();
       // p.insertProductBll(new Product("caise",20,20));
        //try {
         //  p.updateProductBll(3,"piersici",13,30);
       // OrderBLL o=new OrderBLL();
       // o.insertOrderBll(new Orders(3,2,2));
       String s="1 lebenita";
        Pattern ptr=Pattern.compile("(\\d+)");
        Matcher mat=ptr.matcher(s);
        if(mat.find()) {
            int nr = Integer.parseInt(mat.group(1));
            System.out.println(nr);
        }else System.out.println("not");


    }
}