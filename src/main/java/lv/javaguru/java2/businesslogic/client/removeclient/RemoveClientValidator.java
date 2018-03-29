package lv.javaguru.java2.businesslogic.client.removeclient;

import lv.javaguru.java2.dao.ClientDaoInterface;
import lv.javaguru.java2.domens.Client;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveClientValidator {

    private ClientDaoInterface clientDaoImpl;

    public RemoveClientValidator(ClientDaoInterface database) {
        this.clientDaoImpl = database;
    }

    public List<Error> validate(Client client) {
        List<Error> errors = new ArrayList<>();
        validateId(client.getId()).ifPresent(errors::add);
        validateIsExistClientById(client.getId()).ifPresent(errors::add);
        return errors;
    }

    private Optional<Error> validateId(Integer id) {
        if (id == null) {
            return Optional.of(new Error("id", "Must not be null"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateIsExistClientById(Integer id) {
        if (id != null) {
            Optional<Client> foundClient = clientDaoImpl.getClientById(id);
            if (!foundClient.isPresent()) {
                return Optional.of(new Error("id", "Client by id not exist"));
            }
        }
        return Optional.empty();
    }
}
