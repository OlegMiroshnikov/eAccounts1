package lv.javaguru.java2.dao;

import lv.javaguru.java2.domens.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDaoMemoryDatabaseImpl implements ClientDaoInterface {

    private List<Client> clients = new ArrayList<>();

    @Override
    public void addClient(Client client) {
        clients.add(client);
    };

    @Override
    public Optional<Client> getClientById(Integer id) {
        return clients.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    };

    @Override
    public Optional<Client> getClientByPersonalCode(String personalCode) {
        return clients.stream()
                .filter(c -> c.getPersonalCode().equals(personalCode))
                .findFirst();
    };

    @Override
    public void updateClient(Client client) {};

    @Override
    public void removeClient(Client client) {
        clients.remove(client);
    };

    @Override
    public List<Client> getClientList() {
        return clients;
    };
}
