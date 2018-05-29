package lv.javaguru.java2.businesslogic.account.getaccount;

import lv.javaguru.java2.dao.AccountDao;
import lv.javaguru.java2.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class GetAccountService {

    @Autowired
    private AccountDao accountDao;

    @Transactional
    public Optional<Account> getAccountById(Long id) {
        return accountDao.getAccountById(id);
    }
}
