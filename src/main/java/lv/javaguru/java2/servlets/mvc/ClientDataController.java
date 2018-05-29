package lv.javaguru.java2.servlets.mvc;

import lv.javaguru.java2.businesslogic.client.getclient.GetClientService;
import lv.javaguru.java2.businesslogic.client.getclientlist.GetClientListService;
import lv.javaguru.java2.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

//@Component
public class ClientDataController implements MVCController{

    @Autowired
    private GetClientService getClientService;

    @Override
    public MVCModel processGet(HttpServletRequest request) {
        Optional<Client> client = getClientService.getClientById(1);
        return new MVCModel("/clientdata.jsp", client.get());
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }

}
