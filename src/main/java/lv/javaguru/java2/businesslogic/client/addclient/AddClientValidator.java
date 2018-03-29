package lv.javaguru.java2.businesslogic.client.addclient;

import lv.javaguru.java2.dao.ClientDaoInterface;
import lv.javaguru.java2.domens.Client;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddClientValidator {

    private ClientDaoInterface clientDaoImpl;

//    public AddClientValidator(ClientDaoInterface database) {
//        this.clientDaoImpl = database;
//    }

    public List<Error> validate(Client client) {
        List<Error> errors = new ArrayList<>();
        validatePersonalCode(client.getPersonalCode()).ifPresent(errors::add);
        validateName(client.getName()).ifPresent(errors::add);
        validateAddress(client.getAddress()).ifPresent(errors::add);
        validateEMail(client.getEMail()).ifPresent(errors::add);
        validateDuplicatePersonalCode(client.getPersonalCode()).ifPresent(errors::add);
        return errors;
    }

    private Optional<Error> validatePersonalCode(String personalCode) {
        if (personalCode == null || personalCode.trim().isEmpty()) {
            return Optional.of(new Error("personalCode", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Optional.of(new Error("name", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            return Optional.of(new Error("address", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateEMail(String eMail) {
        if (eMail == null || eMail.trim().isEmpty()) {
            return Optional.of(new Error("eMail", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateDuplicatePersonalCode(String personalCode) {
        if (personalCode != null && !personalCode.trim().isEmpty()) {
            Optional<Client> client = clientDaoImpl.getClientByPersonalCode(personalCode);
            if (client.isPresent()) {
                return Optional.of(new Error("personalCode", "Must not be repeated"));
            }
        }
        return Optional.empty();
    }


}
