package lv.javaguru.java2.businesslogic.client.getclient;

import lv.javaguru.java2.dao.ClientDao;
import lv.javaguru.java2.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class GetClientService {
    @Autowired
    private ClientDao clientDao;

    @Transactional
    public Optional<Client> getClientById(Integer id) {
        return clientDao.getClientById(id);
    }
}
