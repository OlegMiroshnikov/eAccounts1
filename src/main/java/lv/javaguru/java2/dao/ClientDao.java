package lv.javaguru.java2.dao;

import lv.javaguru.java2.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDao {
    void addClient(Client client);

    void updateClient(Client client);

    void removeClient(Integer id);

    Optional<Client> getClientById(Integer id);

    Optional<Client> getClientByPersonalCode(String personalCode);

    List<Client> getClientList();
}
