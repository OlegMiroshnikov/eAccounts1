package lv.javaguru.java2.dao;

import lv.javaguru.java2.domens.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDaoInterface {
    void addClient(Client client);

    Optional<Client> getClientById(Integer id);

    Optional<Client> getClientByPersonalCode(String personalCode);

    void updateClient(Client client);

    void removeClient(Client client);

    List<Client> getClientList();
}
