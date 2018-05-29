package lv.javaguru.java2.domain.builders;

import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.Contract;

import java.util.List;

public class CompanyBuilder {

    private Integer id;
    private String regNr;
    private String name;
    private String address;
    private String eMail;
    private String pathFromAccounts;
    private String pathToAccounts;
    List<Contract> contracts;

    private CompanyBuilder() {
    }

    public static CompanyBuilder createCompany() {
        return new CompanyBuilder();
    }

    public Company build() {
        Company company = new Company();
        company.setId(id);
        company.setRegNr(regNr);
        company.setName(name);
        company.setAddress(address);
        company.setEMail(eMail);
        company.setPathFromAccounts(pathFromAccounts);
        company.setPathToAccounts(pathToAccounts);
        company.setContracts(contracts);
        return company;
    }

    public CompanyBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public CompanyBuilder withRegNr(String regNr) {
        this.regNr = regNr;
        return this;
    }

    public CompanyBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CompanyBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public CompanyBuilder withEMail(String eMail) {
        this.eMail = eMail;
        return this;
    }

    public CompanyBuilder withPathFromAccounts(String pathFromAccounts) {
        this.pathFromAccounts = pathFromAccounts;
        return this;
    }

    public CompanyBuilder withPathToAccounts(String pathToAccounts) {
        this.pathToAccounts = pathToAccounts;
        return this;
    }

    public CompanyBuilder with(List<Contract> contracts) {
        this.contracts = contracts;
        return this;
    }

}
