package lv.javaguru.java2.businesslogic.account.removeaccount;

import lv.javaguru.java2.dao.AccountDaoInterface;
import lv.javaguru.java2.domens.Account;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RemoveAccountServiceTest {
    private AccountDaoInterface database;
    private RemoveAccountValidator validator;
    private RemoveAccountService service;
    private Account account;

    @Before
    public void init() {
        database = Mockito.mock(AccountDaoInterface.class);
        validator = Mockito.mock(RemoveAccountValidator.class);
        service = new RemoveAccountService(database, validator);
        account = new Account(1L, 1, "fileName");
    }

    @Test
    public void removeAccount_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(account))
                .thenReturn(errors);
        RemoveAccountResponse response = service.removeAccount(account);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void removeAccount_areErrors_fail() {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("id", "Must not be null"));
        Mockito.when(validator.validate(account))
                .thenReturn(errors);
        RemoveAccountResponse response = service.removeAccount(account);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }


}