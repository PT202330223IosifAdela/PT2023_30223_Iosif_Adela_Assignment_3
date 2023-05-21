package org.example;

import dataAccessLayer.ProductDAO;
import model.Products;

public class Main {
    public static void main(String[] args) {
        ProductDAO pr = new ProductDAO();
        Products p1 = new Products();
       /* p1.setName("Morcovi");
        p1.setStock(225);
        pr.insert(p1);

        Products p2 = new Products();
        p2.setName("Telina");
        p2.setStock(23);
        pr.insert(p2);*/

        p1 = pr.findById(1);

        pr.decrementStock(5, p1);



        /*if(p.getStock() == 23){
            System.out.println("MERGE");
        }
        else{
            System.out.println("NU MERGE");
        }*/

    }
}