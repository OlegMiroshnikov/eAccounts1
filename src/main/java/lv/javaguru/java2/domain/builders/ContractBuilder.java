package lv.javaguru.java2.domain.builders;

import lv.javaguru.java2.domain.Account;
import lv.javaguru.java2.domain.Client;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.Contract;

import java.util.Date;
import java.util.List;

public class ContractBuilder {

    private Integer id;
    private Integer companyId;
    private Integer clientId;
    private String number;
    private Date dateSign;
    private Date dateBegin;
    private Date dateEnd;
    private Integer dayToSendAccount;
    private Integer countDaysToSendReminder;
    private Integer status;
    private Company company;
    private Client client;
    private List<Account> accounts;

    private ContractBuilder() {
    }

    public static ContractBuilder createContract() {
        return new ContractBuilder();
    }

    public Contract build() {
        Contract contract = new Contract();
        contract.setId(id);
        contract.setCompanyId(companyId);
        contract.setClientId(clientId);
        contract.setNumber(number);
        contract.setDateSign(dateSign);
        contract.setDateBegin(dateBegin);
        contract.setDateEnd(dateEnd);
        contract.setDayToSendAccount(dayToSendAccount);
        contract.setCountDaysToSendReminder(countDaysToSendReminder);
        contract.setStatus(status);
        contract.setCompany(company);
        contract.setClient(client);
        contract.setAccounts(accounts);
        return contract;
    }

    public ContractBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ContractBuilder withCompanyId(Integer companyId) {
        this.companyId = companyId;
        return this;
    }

    public ContractBuilder withClientId(Integer clientId) {
        this.clientId = clientId;
        return this;
    }

    public ContractBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public ContractBuilder withDateSign(Date dateSign) {
        this.dateSign = dateSign;
        return this;
    }

    public ContractBuilder withDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
        return this;
    }

    public ContractBuilder withDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public ContractBuilder withDayToSendAccount(Integer dayToSendAccount) {
        this.dayToSendAccount = dayToSendAccount;
        return this;
    }

    public ContractBuilder withCountDaysToSendReminder(Integer countDaysToSendReminder) {
        this.countDaysToSendReminder = countDaysToSendReminder;
        return this;
    }

    public ContractBuilder withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public ContractBuilder with(Company company) {
        this.company = company;
        return this;
    }

    public ContractBuilder with(Client client) {
        this.client = client;
        return this;
    }

    public ContractBuilder with(List<Account> accounts) {
        this.accounts = accounts;
        return this;
    }

}
