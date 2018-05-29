package lv.javaguru.java2.domain.builders;

import lv.javaguru.java2.domain.Client;
import lv.javaguru.java2.domain.Contract;

import java.util.List;

public class ClientBuilder {
    private Integer id;
    private String personalCode;
    private String name;
    private String address;
    private String eMail;
    List<Contract> contracts;

    private ClientBuilder() {
    }

    public static ClientBuilder createClient() {
        return new ClientBuilder();
    }

    public Client build() {
        Client client = new Client();
        client.setId(id);
        client.setPersonalCode(personalCode);
        client.setName(name);
        client.setAddress(address);
        client.setEMail(eMail);
        client.setContracts(contracts);
        return client;
    }

    public ClientBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ClientBuilder withPersonalCode(String personalCode) {
        this.personalCode = personalCode;
        return this;
    }

    public ClientBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public ClientBuilder withEMail(String eMail) {
        this.eMail = eMail;
        return this;
    }

    public ClientBuilder with(List<Contract> contracts) {
        this.contracts = contracts;
        return this;
    }

}
