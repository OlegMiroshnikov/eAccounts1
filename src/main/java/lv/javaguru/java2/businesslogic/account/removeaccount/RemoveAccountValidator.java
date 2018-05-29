package lv.javaguru.java2.businesslogic.account.removeaccount;

import lv.javaguru.java2.dao.AccountDao;
import lv.javaguru.java2.domain.Account;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveAccountValidator {

    @Autowired
    private AccountDao accountDao;

    public List<Error> validate(RemoveAccountRequest request) {
        List<Error> errors = new ArrayList<>();
        validateId(request.getId()).ifPresent(errors::add);
        validateIsExistAccountById(request.getId()).ifPresent(errors::add);
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
            Optional<Account> foundAccount = accountDao.getAccountById(id);
            if (!foundAccount.isPresent()) {
                return Optional.of(new Error("id", "Account by id not exist"));
            }
        }
        return Optional.empty();
    }
}
