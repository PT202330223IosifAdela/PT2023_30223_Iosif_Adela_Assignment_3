package businessLayer;

import dataAccessLayer.ClientDAO;
import model.Clients;

import java.util.List;

public class ClientBLL {
    ClientDAO clientdao;

    public void insertClient(Clients c) {
        clientdao.insert(c);
    }
    public boolean deleteClient(Integer id){
        return clientdao.delete(id);
    }
    public void updateClient(String f, String val, Integer id){
        clientdao.update(f, val, id);
    }

    public Clients findById(int id){
        return clientdao.findById(id);
    }
    public List<Clients> findAll(){
        return clientdao.findAll();
    }
}
