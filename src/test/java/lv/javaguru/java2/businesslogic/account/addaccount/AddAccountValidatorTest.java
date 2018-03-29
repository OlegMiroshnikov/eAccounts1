package lv.javaguru.java2.businesslogic.account.addaccount;

import lv.javaguru.java2.dao.AccountDaoInterface;
import lv.javaguru.java2.domens.Account;
import lv.javaguru.java2.domens.Company;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class AddAccountValidatorTest {
    private AccountDaoInterface accountDaoImpl;
    private AddAccountValidator validator;
    private Company company;
    private Account account;

    @Before
    public void init() {
        accountDaoImpl = Mockito.mock(AccountDaoInterface.class);
        validator = new AddAccountValidator(accountDaoImpl);
        account = new Account(1L, 1, "testAccount");
        company = new Company();
        company.setPathToAccounts("src\\test\\resources\\accounts\\pathToAccounts");
        Mockito.when(accountDaoImpl.getCompanyByContractId(1))
                .thenReturn(Optional.ofNullable(company));
    }

    @Test
    public void validate_accountIsOK_noErrors() {
        List<Error> errors = validator.validate(account);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void validate_contractIdIsNull_error() {
        account.setContractId(null);
        List<Error> errors = validator.validate(account);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "contractId");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be null");
    }

    @Test
    public void validate_fileNameIsNull_error() {
        account.setFileName(null);
        List<Error> errors = validator.validate(account);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "fileName");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        account.setFileName(" ");
        errors = validator.validate(account);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "fileName");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_fileNotExistInCompanyPathToAccountsDirectory_error() {
        account.setFileName("_testAccount");
        List<Error> errors = validator.validate(account);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "fileName");
        assertEquals(errors.get(0).getErrorMessage(), "Must be exist");
    }

    @Test
    public void validate_fileExistInCompanyPathToAccountsDirectory_noErrors() {
        account.setFileName("testAccount");
        List<Error> errors = validator.validate(account);
        assertEquals(errors.size(), 0);
    }

}
