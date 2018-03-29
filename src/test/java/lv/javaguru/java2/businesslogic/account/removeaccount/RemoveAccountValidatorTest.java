package lv.javaguru.java2.businesslogic.account.removeaccount;

import lv.javaguru.java2.dao.AccountDaoInterface;
import lv.javaguru.java2.domens.Account;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class RemoveAccountValidatorTest {
    private AccountDaoInterface database;
    private RemoveAccountValidator validator;
    private Account account;

    @Before
    public void init() {
        database = Mockito.mock(AccountDaoInterface.class);
        validator = new RemoveAccountValidator(database);
        account = new Account(1L, 1, "fileName");
    }

    @Test
    public void validate_idIsNull_error() {
        account.setId(null);
        List<Error> errors = validator.validate(account);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be null");
    }

    @Test
    public void validate_notExistAccountById_error() {
        Mockito.when(database.getAccountById(1L))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(account);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Account by id not exist");
    }

    @Test
    public void validate_existAccountById_noErrors() {
        Account foundAccount = new Account(1L, 1, "fileName");
        Mockito.when(database.getAccountById(1L))
                .thenReturn(Optional.of(foundAccount));
        List<Error> errors = validator.validate(account);
        assertEquals(errors.size(), 0);
    }

}
