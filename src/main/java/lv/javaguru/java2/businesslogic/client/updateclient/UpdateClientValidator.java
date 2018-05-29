package lv.javaguru.java2.businesslogic.client.updateclient;

import lv.javaguru.java2.dao.ClientDao;
import lv.javaguru.java2.domain.Client;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateClientValidator {
    @Autowired
    private ClientDao clientDao;

    public List<Error> validate(UpdateClientRequest request) {
        List<Error> errors = new ArrayList<>();
        validateId(request.getId()).ifPresent(errors::add);
        validateIsExistClientById(request.getId()).ifPresent(errors::add);
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
            Optional<Client> foundClient = clientDao.getClientById(id);
            if (!foundClient.isPresent()) {
                return Optional.of(new Error("id", "Client by id not exist"));
            }
        }
        return Optional.empty();
    }

}
