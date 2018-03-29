package lv.javaguru.java2.businesslogic.account.removeaccount;

import lv.javaguru.java2.dao.AccountDaoInterface;
import lv.javaguru.java2.domens.Account;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveAccountValidator {

    private AccountDaoInterface accountDaoImpl;

    public RemoveAccountValidator(AccountDaoInterface database) {
        this.accountDaoImpl = database;
    }

    public List<Error> validate(Account account) {
        List<Error> errors = new ArrayList<>();
        validateId(account.getId()).ifPresent(errors::add);
        validateIsExistAccountById(account.getId()).ifPresent(errors::add);
        return errors;
    }

    private Optional<Error> validateId(Long id) {
        if (id == null) {
            return Optional.of(new Error("id", "Must not be null"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateIsExistAccountById(Long id) {
        if (id != null) {
            Optional<Account> foundAccount = accountDaoImpl.getAccountById(id);
            if (!foundAccount.isPresent()) {
                return Optional.of(new Error("id", "Account by id not exist"));
            }
        }
        return Optional.empty();
    }
}
