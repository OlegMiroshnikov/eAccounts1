package lv.javaguru.java2.businesslogic.client.updateclient;

import lv.javaguru.java2.dao.ClientDao;
import lv.javaguru.java2.domain.Client;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.javaguru.java2.domain.builders.ClientBuilder.createClient;

@Component
public class UpdateClientService {
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private UpdateClientValidator updateClientValidator;

    @Transactional
    public UpdateClientResponse updateClient(UpdateClientRequest request) {
        List<Error> errors = updateClientValidator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateClientResponse(false, errors);
        }
        Client client = createClient()
                .withId(request.getId())
                .withPersonalCode(request.getPersonalCode())
                .withName(request.getName())
                .withAddress(request.getAddress())
                .withEMail(request.geteMail())
                .build();
        clientDao.updateClient(client);
        return new UpdateClientResponse(true, null);
    }
}
