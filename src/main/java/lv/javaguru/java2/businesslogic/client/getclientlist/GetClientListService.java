package lv.javaguru.java2.businesslogic.client.getclientlist;

import lv.javaguru.java2.dao.ClientDaoInterface;
import lv.javaguru.java2.domens.Client;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetClientListService {

    private ClientDaoInterface clientDaoImpl;

    public GetClientListService(ClientDaoInterface database) {
        this.clientDaoImpl = database;
    }

    public List<Client> getClientList() {
        return clientDaoImpl.getClientList();
    }
}
