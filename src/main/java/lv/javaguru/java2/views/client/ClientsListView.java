package lv.javaguru.java2.views.client;

import lv.javaguru.java2.businesslogic.client.getclientlist.GetClientListService;
import lv.javaguru.java2.domens.Client;
import lv.javaguru.java2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientsListView implements View {

    @Autowired
    private GetClientListService service;

//    public ClientsListView(ClientDaoInterface database) {
//        this.service = new GetClientListService(database);
//    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Print clients list to console execution start!");
        for (Client client : service.getClientList()) {
            System.out.println(client);
        }
        System.out.println("Print client list to console execution end!");
        System.out.println();
    }
}

