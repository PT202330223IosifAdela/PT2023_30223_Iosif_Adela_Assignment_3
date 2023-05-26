package businessLayer;

import dataAccessLayer.OrderDAO;
import model.Orders;

import java.util.List;

/**
 * Aceasta clasa este necesara pentru a grupa toate metodele necesare implementarii operatiilor
 * pentru comenzi
 * apeleaza metode care deja implementeaza interogari pe baza de date
 * este folosita mai departe in interfata
 */
public class OrderBLL {
    private OrderDAO orderdao = new OrderDAO();

    public void insertOrder(Orders o) {
        orderdao.insert(o);
    }
    public boolean deleteOrder(Integer id){
        return orderdao.delete(id);
    }
    public void updateOrder(String f, String val, Integer id){
        orderdao.update(f, val, id);
    }

    public Orders findById(int id){
        return orderdao.findById(id);
    }
    public List<Orders> findAll(){
        return orderdao.findAll();
    }
}
