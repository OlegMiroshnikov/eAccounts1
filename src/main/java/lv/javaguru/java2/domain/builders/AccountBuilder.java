package lv.javaguru.java2.domain.builders;

import lv.javaguru.java2.domain.Account;
import lv.javaguru.java2.domain.Contract;

import java.util.Date;

public class AccountBuilder {

    private Long id;
    private Integer contactId;
    private String fileName;
    private Date dateSending;
    private Date dateViewing;
    private Date dateReminderSending;
    private Contract contract;

    private AccountBuilder() {
    }

    public static AccountBuilder createAccount() {
        return new AccountBuilder();
    }

    public Account build() {
        Account account = new Account();
        account.setId(id);
        account.setContractId(contactId);
        account.setFileName(fileName);
        account.setDateSending(dateSending);
        account.setDateViewing(dateViewing);
        account.setDateReminderSending(dateReminderSending);
        account.setContract(contract);
        return account;
    }

    public AccountBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AccountBuilder withFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public AccountBuilder withContractId(Integer contactId) {
        this.contactId = contactId;
        return this;
    }
    public AccountBuilder withDateSendind(Date dateSending) {
        this.dateSending = dateSending;
        return this;
    }

    public AccountBuilder withDateViewing(Date dateViewing) {
        this.dateViewing = dateViewing;
        return this;
    }

    public AccountBuilder withDateReminderSendind(Date dateReminderSendind) {
        this.dateReminderSending = dateReminderSendind;
        return this;
    }

    public AccountBuilder with(Contract contract) {
        this.contract = contract;
        return this;
    }
}
