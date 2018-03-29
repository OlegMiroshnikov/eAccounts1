package lv.javaguru.java2.businesslogic.client.removeclient;

import lv.javaguru.java2.dao.ClientDaoInterface;
import lv.javaguru.java2.domens.Client;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveClientService {

    private ClientDaoInterface clientDaoImpl;
    private RemoveClientValidator removeClientValidator;

    public RemoveClientService(ClientDaoInterface database, RemoveClientValidator removeClientValidator) {
        this.clientDaoImpl = database;
        this.removeClientValidator = removeClientValidator;
    }

    public RemoveClientResponse removeClient(Client client) {
        List<Error> errors = removeClientValidator.validate(client);
        if (!errors.isEmpty()) {
            return new RemoveClientResponse(false, errors);
        }
        clientDaoImpl.removeClient(client);
        return new RemoveClientResponse(true, null);
    }
}
