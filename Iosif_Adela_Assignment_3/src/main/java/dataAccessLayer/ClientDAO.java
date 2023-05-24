package dataAccessLayer;

import connection.ConnectionFactory;
import model.Clients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clasa mosteneste metodele generice
 * Implementeaza metoda abstracta
 */
public class ClientDAO extends AbstractDAO<Clients>{
    @Override
    public void insert(Clients clients) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement insStatement = null;

        try{
            insStatement = connection.prepareStatement("INSERT INTO clients (name, phoneNumber)" + " VALUES (?, ?)",Statement.RETURN_GENERATED_KEYS);
            insStatement.setString(1, clients.getName());
            insStatement.setString(2, clients.getPhoneNumber());
            insStatement.execute();
        } catch (SQLException e) {
            System.out.println("Nu se poate insera in tabela clients!");
        }
        finally {
            ConnectionFactory.close(insStatement);
            ConnectionFactory.close(connection);
        }

    }
}
