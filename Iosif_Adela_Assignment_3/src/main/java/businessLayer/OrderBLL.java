package businessLayer;

import dataAccessLayer.OrderDAO;
import model.Orders;

import java.util.List;

public class OrderBLL {
    OrderDAO orderdao;

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
