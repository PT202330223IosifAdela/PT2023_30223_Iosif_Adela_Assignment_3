package dataAccessLayer;

import connection.ConnectionFactory;
import model.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clasa mosteneste metodele generice
 * Implementeaza metoda abstracta
 */
public class OrderDAO extends AbstractDAO<Orders>{
    @Override
    public void insert(Orders orders) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement insStatement = null;

        try{
            insStatement = connection.prepareStatement("INSERT INTO orders (quantity)" + " VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            insStatement.setInt(1, orders.getQuantity());
            insStatement.execute();
        } catch (SQLException e) {
            System.out.println("Nu se poate insera in tabela orders!");
        }
        finally {
            ConnectionFactory.close(insStatement);
            ConnectionFactory.close(connection);
        }

    }
}
