package dataAccessLayer;

import connection.ConnectionFactory;
import model.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDAO extends AbstractDAO<Products>{
    private int productId;
    private String name;
    private int stock;

    /**
     * Metoda care decrementeaza produsele din stoc
     * @param cantitate - cantitatea care trebuie scazuta din stocul unui anumit produs
     */
    public void decrementStock(int cantitate){
        if(stock >= cantitate){
            stock = stock - cantitate;
        }
        else{
            System.out.println("Stoc insuficient"); //eroare in interfata
        }
    }

    @Override
    public void insert(Products t) {
       Connection connection = ConnectionFactory.getConnection();
       PreparedStatement insStatement = null;

       try{
           insStatement = connection.prepareStatement("INSERT INTO products (name, stock)" + " VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
           insStatement.setString(1, t.getName());
           insStatement.setInt(2, t.getStock());
           insStatement.execute();
       } catch (SQLException e) {
            System.out.println("Nu se poate insera!");
       }
       finally {
           ConnectionFactory.close(insStatement);
           ConnectionFactory.close(connection);
       }
    }
}
