package lv.javaguru.java2.businesslogic.client.getclientlist;

import lv.javaguru.java2.dao.ClientDao;
import lv.javaguru.java2.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class GetClientListService {

    @Autowired
    private ClientDao clientDao;

    @Transactional
    public List<Client> getClientList() {
        return clientDao.getClientList();
    }

    @Transactional
    public Optional<Client> getClientById(Integer id) {
        return clientDao.getClientById(id);
    }

}
