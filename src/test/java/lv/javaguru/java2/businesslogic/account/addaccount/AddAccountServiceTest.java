package lv.javaguru.java2.businesslogic.account.addaccount;

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
public class AddAccountServiceTest {

    @Mock
    private AccountDao accountDao;

    @Mock
    private AddAccountValidator validator;

    @InjectMocks
    private AddAccountService service;

    private AddAccountRequest request;

    @Before
    public void init() {
        request = new AddAccountRequest( 1, "fileName");
    }

    @Test
    public void addAccount_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        AddAccountResponse response = service.addAccount(request);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void addAccount_areErrors_fail() {
        request.setContractId(null);
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("contractId", "Must not be empty"));
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        AddAccountResponse response = service.addAccount(request);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }

}