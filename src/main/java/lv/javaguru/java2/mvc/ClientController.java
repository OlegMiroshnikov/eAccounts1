package lv.javaguru.java2.mvc;

import lv.javaguru.java2.businesslogic.client.addclient.AddClientRequest;
import lv.javaguru.java2.businesslogic.client.addclient.AddClientResponse;
import lv.javaguru.java2.businesslogic.client.addclient.AddClientService;
import lv.javaguru.java2.businesslogic.client.getclient.GetClientService;
import lv.javaguru.java2.businesslogic.client.getclientlist.GetClientListService;
import lv.javaguru.java2.businesslogic.client.removeclient.RemoveClientRequest;
import lv.javaguru.java2.businesslogic.client.removeclient.RemoveClientResponse;
import lv.javaguru.java2.businesslogic.client.removeclient.RemoveClientService;
import lv.javaguru.java2.businesslogic.client.updateclient.UpdateClientRequest;
import lv.javaguru.java2.businesslogic.client.updateclient.UpdateClientResponse;
import lv.javaguru.java2.businesslogic.client.updateclient.UpdateClientService;
import lv.javaguru.java2.domain.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private AddClientService addClientService;
    @Autowired
    private UpdateClientService updateClientService;
    @Autowired
    private RemoveClientService removeClientService;
    @Autowired
    private GetClientService getClientService;
    @Autowired
    private GetClientListService getClientListService;

    @RequestMapping(value = {"/", "/index"})
    public String showHomePage(Map<String, Object> model) {
        return "/index";
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String listClients(ModelMap model) {
        model.addAttribute("client", new Client());
        model.addAttribute("listClients", this.getClientListService.getClientList());
        return "/clients";
    }

    @RequestMapping(value = "/client/add", method = RequestMethod.POST)
    public String addClient(@ModelAttribute("client") Client client) {
        if (client.getId() == null || client.getId() == 0) {
            AddClientRequest addClientRequest = new AddClientRequest(
                    client.getPersonalCode(),
                    client.getName(),
                    client.getAddress(),
                    client.geteMail());
            AddClientResponse response = this.addClientService.addClient(addClientRequest);
            if (response.isSuccess()) {
                logger.info("Client successfully saved. Client details: " + client);
            } else {
                response.getErrors().forEach(error -> {
                    logger.error(error.toString());
                });
            }

        } else {
            UpdateClientRequest updateClientRequest = new UpdateClientRequest(
                    client.getId(),
                    client.getPersonalCode(),
                    client.getName(),
                    client.getAddress(),
                    client.geteMail());
            UpdateClientResponse response = this.updateClientService.updateClient(updateClientRequest);
            if (response.isSuccess()) {
                logger.info("Client successfully updated. Client details: " + client);
            } else {
                response.getErrors().forEach(error -> {
                    logger.error(error.toString());
                });
            }
        }
        return "redirect:/clients";
    }

    @RequestMapping("/client/remove/{id}")
    public String removeClient(@PathVariable("id") int id) {
        RemoveClientResponse response = this.removeClientService.removeClient(new RemoveClientRequest(id));
        if (response.isSuccess()) {
            logger.info("Client successfully removed. Client id: " + id);
        } else {
            response.getErrors().forEach(error -> {
                logger.error(error.toString());
            });
        }
        return "redirect:/clients";
    }


    @RequestMapping("/client/edit/{id}")
    public String editClient(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", this.getClientService.getClientById(id).get());
        model.addAttribute("listClients", this.getClientListService.getClientList());
        return "clients";
    }

    @RequestMapping("clientdata/{id}")
    public String clientData(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", this.getClientService.getClientById(id).get());
        return "clientdata";
    }
}

