package lv.javaguru.java2.dao;

import lv.javaguru.java2.domain.Account;
import lv.javaguru.java2.domain.Company;

import java.util.List;
import java.util.Optional;

public interface AccountDao {
    void addAccount(Account account);

    void updateAccount(Account account);

    void removeAccount(Long id);

    Optional<Account> getAccountById(Long id);

    Optional<Company> getCompanyByContractId(Integer id);

    List<Account> getAccountList();

    List<Account> getAccountListByContractId(Integer contractId);
}

