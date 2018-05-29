package lv.javaguru.java2.businesslogic.account.getaccountlist;

import lv.javaguru.java2.dao.AccountDao;
import lv.javaguru.java2.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetAccountListService {

    @Autowired
    private AccountDao accountDao;

    @Transactional
    public List<Account> getAccountList() {
        return accountDao.getAccountList();
    }
}
