package dataAccessLayer;

import java.math.BigDecimal;

public class ProductDAO {
    private int productId;
    private String name;
    private int stock;

    public void decrementStock(int cantitate){
        if(stock >= cantitate){
            stock = stock - cantitate;
        }
        else{
            System.out.println("Stoc insuficient");
        }
    }
}
