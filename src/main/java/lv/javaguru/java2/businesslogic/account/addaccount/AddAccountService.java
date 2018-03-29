package lv.javaguru.java2.businesslogic.account.addaccount;

import lv.javaguru.java2.dao.AccountDaoInterface;
import lv.javaguru.java2.domens.Account;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddAccountService {

    private AccountDaoInterface accountDaoImpl;
    private AddAccountValidator addAccountValidator;

    public AddAccountService(AccountDaoInterface database, AddAccountValidator addAccountValidator) {
        this.accountDaoImpl = database;
        this.addAccountValidator = addAccountValidator;
    }

    public AddAccountResponse addAccount(Account account) {
        List<Error> errors = addAccountValidator.validate(account);
        if (!errors.isEmpty()) {
            return new AddAccountResponse(false, errors);
        }
        accountDaoImpl.addAccount(account);
        return new AddAccountResponse(true, null);
    }
}
