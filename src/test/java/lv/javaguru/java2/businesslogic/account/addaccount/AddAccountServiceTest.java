package lv.javaguru.java2.businesslogic.account.addaccount;

import lv.javaguru.java2.dao.AccountDaoInterface;
import lv.javaguru.java2.domens.Account;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AddAccountServiceTest {
    private AccountDaoInterface database;
    private AddAccountValidator validator;
    private AddAccountService service;
    private Account account;

    @Before
    public void init() {
        database = Mockito.mock(AccountDaoInterface.class);
        validator = Mockito.mock(AddAccountValidator.class);
        service = new AddAccountService(database, validator);
        account = new Account(1L, 1, "fileName");
    }

    @Test
    public void addAccount_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(account))
                .thenReturn(errors);
        AddAccountResponse response = service.addAccount(account);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void addAccount_areErrors_fail() {
        account.setContractId(null);
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("contractId", "Must not be empty"));
        Mockito.when(validator.validate(account))
                .thenReturn(errors);
        AddAccountResponse response = service.addAccount(account);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }

}