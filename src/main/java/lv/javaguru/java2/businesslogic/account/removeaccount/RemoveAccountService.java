package lv.javaguru.java2.businesslogic.account.removeaccount;

import lv.javaguru.java2.dao.AccountDaoInterface;
import lv.javaguru.java2.domens.Account;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveAccountService {

    private AccountDaoInterface accountDaoImpl;
    private RemoveAccountValidator removeAccountValidator;

    public RemoveAccountService(AccountDaoInterface database, RemoveAccountValidator removeAccountValidator) {
        this.accountDaoImpl = database;
        this.removeAccountValidator = removeAccountValidator;
    }

    public RemoveAccountResponse removeAccount(Account account) {
        List<Error> errors = removeAccountValidator.validate(account);
        if (!errors.isEmpty()) {
            return new RemoveAccountResponse(false, errors);
        }
        accountDaoImpl.removeAccount(account);
        return new RemoveAccountResponse(true, null);
    }
}
