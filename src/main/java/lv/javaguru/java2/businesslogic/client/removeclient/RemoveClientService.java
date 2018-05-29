package lv.javaguru.java2.businesslogic.client.removeclient;

import lv.javaguru.java2.dao.ClientDao;
import lv.javaguru.java2.domain.Client;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.javaguru.java2.domain.builders.ClientBuilder.createClient;

@Component
public class RemoveClientService {

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private RemoveClientValidator removeClientValidator;

    @Transactional
    public RemoveClientResponse removeClient(RemoveClientRequest request) {
        List<Error> errors = removeClientValidator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveClientResponse(false, errors);
        }
        clientDao.removeClient(request.getId());
        return new RemoveClientResponse(true, null);
    }
}
