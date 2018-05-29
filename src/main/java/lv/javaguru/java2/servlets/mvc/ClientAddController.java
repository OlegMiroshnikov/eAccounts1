package lv.javaguru.java2.servlets.mvc;

import lv.javaguru.java2.businesslogic.client.addclient.AddClientRequest;
import lv.javaguru.java2.businesslogic.client.addclient.AddClientService;
import lv.javaguru.java2.businesslogic.client.getclientlist.GetClientListService;
import lv.javaguru.java2.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//@Component
public class ClientAddController implements MVCController {

    @Autowired
    private AddClientService addClientService;
    @Autowired
    private GetClientListService getClientListService;

    @Override
    public MVCModel processGet(HttpServletRequest request) {

        AddClientRequest addClientRequest;
        addClientRequest = new AddClientRequest(
                request.getParameter("personalCode"),
                request.getParameter("name"),
                request.getParameter("address"),
                request.getParameter("eMail"));
        addClientService.addClient(addClientRequest);

        List<Client> listClients = getClientListService.getClientList();
        return new MVCModel("/clients.jsp", listClients);
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
//        throw new UnsupportedOperationException();
        AddClientRequest addClientRequest;
        addClientRequest = new AddClientRequest(
                request.getParameter("personalCode"),
                request.getParameter("name"),
                request.getParameter("address"),
                request.getParameter("eMail"));
        addClientService.addClient(addClientRequest);

        List<Client> listClients = getClientListService.getClientList();
        return new MVCModel("/clients.jsp", listClients);

    }
}
