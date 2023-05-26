package businessLayer;

import dataAccessLayer.ClientDAO;
import model.Clients;

import java.util.List;

/**
 * construieste obiectul clientdao care este folosit in apelurile metodelor
 * care serverc la implementarea operatiilor specifice unui client, dar deja implementate
 * clasa este utilizata mai departe in interfata pentru legaturile la tabelele din baza de date
 */
public class ClientBLL {
    private ClientDAO clientdao = new ClientDAO();

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
