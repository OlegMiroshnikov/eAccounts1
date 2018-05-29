package lv.javaguru.java2.businesslogic.account.removeaccount;

import lv.javaguru.java2.dao.AccountDao;
import lv.javaguru.java2.domain.Account;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.javaguru.java2.domain.builders.AccountBuilder.createAccount;

@Component
public class RemoveAccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private RemoveAccountValidator removeAccountValidator;

    @Transactional
    public RemoveAccountResponse removeAccount(RemoveAccountRequest request) {
        List<Error> errors = removeAccountValidator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveAccountResponse(false, errors);
        }
        accountDao.removeAccount(request.getId());
        return new RemoveAccountResponse(true, null);
    }
}
