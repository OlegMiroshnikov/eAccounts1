package lv.javaguru.java2.businesslogic.client.addclient;

import lv.javaguru.java2.dao.ClientDao;
import lv.javaguru.java2.domain.Client;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.javaguru.java2.domain.builders.ClientBuilder.createClient;

@Component
public class AddClientService {

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private AddClientValidator addClientValidator;


    @Transactional
    public AddClientResponse addClient(AddClientRequest request) {
        List<Error> errors = addClientValidator.validate(request);
        if (!errors.isEmpty()) {
            return new AddClientResponse(errors);
        }
        Client client = createClient()
                .withPersonalCode(request.getPersonalCode())
                .withName(request.getName())
                .withAddress(request.getAddress())
                .withEMail(request.getEMail())
                .build();

        clientDao.addClient(client);
        return new AddClientResponse(client.getId());
    }

}

