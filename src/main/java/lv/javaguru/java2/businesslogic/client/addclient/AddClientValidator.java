package lv.javaguru.java2.businesslogic.client.addclient;

import lv.javaguru.java2.dao.ClientDao;
import lv.javaguru.java2.domain.Client;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddClientValidator {

    @Autowired
    private ClientDao clientDao;

    public List<Error> validate(AddClientRequest request) {
        List<Error> errors = new ArrayList<>();
        validatePersonalCode(request.getPersonalCode()).ifPresent(errors::add);
        validateName(request.getName()).ifPresent(errors::add);
        validateEMail(request.getEMail()).ifPresent(errors::add);
        validateDuplicatePersonalCode(request.getPersonalCode()).ifPresent(errors::add);
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

    private Optional<Error> validateEMail(String eMail) {
        if (eMail == null || eMail.trim().isEmpty()) {
            return Optional.of(new Error("eMail", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateDuplicatePersonalCode(String personalCode) {
        if (personalCode != null && !personalCode.trim().isEmpty()) {
            Optional<Client> client = clientDao.getClientByPersonalCode(personalCode);
            if (client.isPresent()) {
                return Optional.of(new Error("personalCode", "Must not be repeated"));
            }
        }
        return Optional.empty();
    }

}
