package lv.javaguru.java2.businesslogic.account.removeaccount;

import lv.javaguru.java2.dao.AccountDao;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RemoveAccountServiceTest {

    @Mock
    private AccountDao accountDao;

    @Mock
    private RemoveAccountValidator validator;

    @InjectMocks
    private RemoveAccountService service;

    private RemoveAccountRequest request;

    @Before
    public void init() {
        request = new RemoveAccountRequest(1L);
    }

    @Test
    public void removeAccount_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        RemoveAccountResponse response = service.removeAccount(request);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void removeAccount_areErrors_fail() {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("id", "Must not be null"));
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        RemoveAccountResponse response = service.removeAccount(request);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }


}