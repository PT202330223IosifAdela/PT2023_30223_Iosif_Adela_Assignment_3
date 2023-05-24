package org.example;

import dataAccessLayer.ClientDAO;
import dataAccessLayer.OrderDAO;
import dataAccessLayer.ProductDAO;
import model.Clients;
import model.Orders;
import model.Products;

public class Main {
    public static void main(String[] args) {
        ProductDAO pr = new ProductDAO();
        Products p1 = new Products();
        p1.setName("Morcovi");
        p1.setStock(225);
        pr.insert(p1);

        Products p2 = new Products();
        p2.setName("Telina");
        p2.setStock(23);
        pr.insert(p2);
        //pr.delete(2);

        ClientDAO cl = new ClientDAO();

        Clients c1 = new Clients();
        c1.setName("Marcel");
        c1.setPhoneNumber("0726373738");
        cl.insert(c1);


        OrderDAO or = new OrderDAO();
        Orders o1 = new Orders();
        o1.setQuantity(10);
        or.insert(o1);

        //p1 = pr.findById(1);

       // pr.decrementStock(5, p1);



        /*if(p.getStock() == 23){
            System.out.println("MERGE");
        }
        else{
            System.out.println("NU MERGE");
        }*/

    }
}