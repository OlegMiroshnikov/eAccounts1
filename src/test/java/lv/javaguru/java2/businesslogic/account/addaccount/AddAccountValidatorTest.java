package lv.javaguru.java2.businesslogic.account.addaccount;

import lv.javaguru.java2.dao.AccountDao;
import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.Contract;
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
public class AddAccountValidatorTest {

    @Mock
    private AccountDao accountDao;

    @Mock
    private ContractDao contractDao;

    @InjectMocks
    private AddAccountValidator validator;

    private AddAccountRequest request;

    @Before
    public void init() {
        request = new AddAccountRequest( 1, "testAccount");
        Company company = new Company();
        company.setPathToAccounts("src\\test\\resources\\accounts\\pathToAccounts");
        Contract contract = new Contract();
        Mockito.when(accountDao.getCompanyByContractId(1))
                .thenReturn(Optional.of(company));
        Mockito.when(contractDao.getContractById(1))
                .thenReturn(Optional.of(contract));
    }

    @Test
    public void validate_accountIsOK_noErrors() {
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void validate_contractIdIsNull_isError() {
        request.setContractId(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "contractId");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be null");
    }

    @Test
    public void validate_contractByIdNotExist_isError() {
        Mockito.when(contractDao.getContractById(1))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "account");
        assertEquals(errors.get(0).getErrorMessage(), "Contract must be exist");
    }

    @Test
    public void validate_fileNameIsNull_isError() {
        request.setFileName(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "fileName");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        request.setFileName(" ");
        errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "fileName");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_fileNotExistInCompanyPathToAccountsDirectory_isError() {
        request.setFileName("_testAccount");
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "fileName");
        assertEquals(errors.get(0).getErrorMessage(), "Must be exist");
    }

    @Test
    public void validate_fileExistInCompanyPathToAccountsDirectory_noErrors() {
        request.setFileName("testAccount");
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

}
