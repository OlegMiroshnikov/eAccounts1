package lv.javaguru.java2.businesslogic.account.getaccountlist;

import lv.javaguru.java2.dao.AccountDaoInterface;
import lv.javaguru.java2.domens.Account;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAccountListService {

    private AccountDaoInterface accountDaoImpl;

    public GetAccountListService(AccountDaoInterface database) {
        this.accountDaoImpl = database;
    }

    public List<Account> getAccountList() {
        return accountDaoImpl.getAccountList();
    }
}
