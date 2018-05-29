package lv.javaguru.java2.businesslogic.account.removeaccount;

import lv.javaguru.java2.dao.AccountDao;
import lv.javaguru.java2.domain.Account;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RemoveAccountValidatorTest {

    @Mock
    private AccountDao accountDao;

    @InjectMocks
    private RemoveAccountValidator validator;

    private RemoveAccountRequest request;

    @Before
    public void init() {
        request = new RemoveAccountRequest(1L);
    }

    @Test
    public void validate_idIsNull_isError() {
        request.setId(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be null");
    }

    @Test
    public void validate_notExistAccountById_isError() {
        Mockito.when(accountDao.getAccountById(1L))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Account by id not exist");
    }

    @Test
    public void validate_existAccountById_noErrors() {
        Account foundAccount = new Account(1L, 1, "fileName");
        Mockito.when(accountDao.getAccountById(1L))
                .thenReturn(Optional.of(foundAccount));
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

}
