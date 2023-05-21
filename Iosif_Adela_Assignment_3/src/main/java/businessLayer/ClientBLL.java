package businessLayer;

import dataAccessLayer.ClientDAO;
import model.Clients;

public class ClientBLL {
    ClientDAO clientdao;

    public void insertClient(Clients c) {
        clientdao.insert(c);
    }
}
