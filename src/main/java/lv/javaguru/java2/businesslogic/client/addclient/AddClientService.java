package lv.javaguru.java2.businesslogic.client.addclient;

import lv.javaguru.java2.dao.ClientDaoInterface;
import lv.javaguru.java2.domens.Client;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddClientService {

    private ClientDaoInterface clientDaoImpl;
    private AddClientValidator addClientValidator;

//    public AddClientService(ClientDaoInterface database, AddClientValidator addClientValidator) {
//        this.clientDaoImpl = database;
//        this.addClientValidator = addClientValidator;
//    }

    public AddClientResponse addClient(Client client) {
        List<Error> errors = addClientValidator.validate(client);
        if (!errors.isEmpty()) {
            return new AddClientResponse(false, errors);
        }
        clientDaoImpl.addClient(client);
        return new AddClientResponse(true, null);
    }

//    public Optional<Client> getClientById(Integer id) {
//        return clientDaoImpl.getClientById(id);
//    }
//
//    public Optional<Client> getClientByPersonalCode(String personalCode) {
//        return clientDaoImpl.getClientByPersonalCode(personalCode);
//    }
//
//    public void updateClient(Client client) {
//        clientDaoImpl.updateClient(client);
//    }

}
