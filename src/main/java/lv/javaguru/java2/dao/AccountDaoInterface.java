package lv.javaguru.java2.dao;

import lv.javaguru.java2.domens.Account;
import lv.javaguru.java2.domens.Company;

import java.util.List;
import java.util.Optional;

public interface AccountDaoInterface {
    void addAccount(Account account);

    Optional<Account> getAccountById(Long id);

    Optional<Company> getCompanyByContractId(Integer id);

    void updateAccount(Account account);

    void removeAccount(Account account);

    List<Account> getAccountList();

    List<Account> getAccountListByContractId(Integer contractId);
}

