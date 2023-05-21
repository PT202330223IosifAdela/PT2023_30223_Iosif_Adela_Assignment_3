package dataAccessLayer;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import model.Products;

/**
 * Clasa generica folosita pt accesarea bazei de date
 * @param <T>
 */
public abstract class AbstractDAO<T> {

    private final Class<T> type;

    /**
     * Constructor care extrage clasa model pt care se implementeaza operatiile.
     *
     */

    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Metoda creeaza o interogare generica de select all
     * @return textul interogarii
     */
    private String createSelectQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Interogare generica conditionata
     * Cauta in baza de date dupa campul primit
     * @param field - o coloana dupa care se va face cautarea in bd
     * @return textul selectat dupa interogare
     */
    private String createSelectQueryField(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Primeste id-ul inregistrarii care trebuie sters din bd
     * Se creeaza o interogare generica de stergere
     * @param id
     * @return
     */
    private String createDeleteQ(Integer id){
        StringBuilder s = new StringBuilder();
        s.append("DELETE FROM ");
        s.append(type.getSimpleName());
        s.append(" WHERE id=" + id);

        return s.toString();
    }

    /**
     * Metoda generica
     * Gaseste toate inregistrarile din tabela corespunzatoare
     * clasei generice
     * @return
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet resSet = null;
        String q = createSelectQuery();

        try{
            connection = ConnectionFactory.getConnection();
            st = connection.prepareStatement(q);
            resSet = st.executeQuery();

            return createObjects(resSet);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            ConnectionFactory.close(resSet);
            ConnectionFactory.close(st);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda generica care gaseste o inregistrare in baza de date
     * dupa id-ul furnizat
     * @param id
     * @return
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQueryField("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    /**
     * Creeaza un obiect pt clasa generica, pe baza val date
     * @param resultSet - rezultatul interogarii
     * @return
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Metoda abstracta
     * Primeste un obiect al clasei generice
     * Incearca sa insereze obiectul primit in baza de date
     * @param t - inregistrarea care trebuie inserata in baza de date
     */
    public abstract void insert(T t);

    /**
     * ??
     *
     * @param field - coloana din bd care trebuie modificata
     * @param id - id-ul obiectului care trebuie modificat
     * @return
     */
    private String createUpdateQ(String field, Integer id) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        sb.append(field);
        sb.append(" = ?");
        sb.append(" WHERE id = " + id);
        return sb.toString();
    }


    /**
     * Metoda generica
     * Face update la o inreg din baza de date
     * @param field - numele campului care trebuie modificat
     * @param val - valoarea actualizata
     * @param id - id-ul inregistrarii initiale
     */
    public void update(String field, String val, Integer id) {
        T obj = findById(id);
        if(obj == null){
            return;
        }

        Connection connection = null;
        PreparedStatement statement = null;

        String query = createUpdateQ(field, id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, val);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Metoda generica
     * Sterge inregistrarea cu id-ul dat
     * @param id
     * @return - flag ca sa stim daca s-a efectuat sau nu operatia
     */
    public boolean delete(Integer id){
        T t = findById(id);

        if(t == null){
            return false;
        }
        Connection c = null;
        PreparedStatement s = null;
        String q = createDeleteQ(id);

        try{
            c = ConnectionFactory.getConnection();
            s = c.prepareStatement(q);
            s.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionFactory.close(s);
            ConnectionFactory.close(c);
        }
    }

}
