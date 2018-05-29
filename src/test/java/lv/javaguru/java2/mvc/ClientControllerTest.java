package lv.javaguru.java2.mvc;

import lv.javaguru.java2.businesslogic.client.getclientlist.GetClientListService;
import lv.javaguru.java2.businesslogic.company.getcompany.GetCompanyService;
import lv.javaguru.java2.dao.ClientDao;
import lv.javaguru.java2.domain.Client;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.HashMap;

import static lv.javaguru.java2.domain.builders.ClientBuilder.createClient;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientControllerTest {

    @InjectMocks
    private ClientController controller;
    @Mock
    private GetClientListService getClientListService;

    @Test
    public void showHomePage_returnIndexPage_OK() {
        String viewName = controller.showHomePage(new HashMap<String, Object>());
        assertEquals("/index", viewName);
    }
    @Test
    public void listClients_returnClientsPage_OK() {
        ArrayList<Client> listClients = new ArrayList<>();
        Client client = createClient()
                .withId(1)
                .withPersonalCode("personalCode")
                .withName("name")
                .withAddress("address")
                .withEMail("eMail")
                .build();
        listClients.add(client);
        Mockito.when(getClientListService.getClientList())
                .thenReturn(listClients);
        ModelMap model = new ModelMap();
        String viewName = controller.listClients(model);
        assertEquals("/clients", viewName);
        assertSame(listClients, model.get("listClients"));
    }

}