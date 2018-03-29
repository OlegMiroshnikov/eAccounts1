package lv.javaguru.java2.businesslogic.company.addcompany;

import lv.javaguru.java2.dao.CompanyDaoInterface;
import lv.javaguru.java2.domens.Company;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class AddCompanyValidatorTest {

    private CompanyDaoInterface database;
    private AddCompanyValidator validator;
    private Company company;

    @Before
    public void init() {
        database = Mockito.mock(CompanyDaoInterface.class);
        validator = new AddCompanyValidator(database);
        company = new Company(1, "regNr", "name", "address", "eMail",
                "pathFromAccounts", "pathToAccounts");

    }

    @Test
    public void validate_companyIsOK_noErrors() {
        List<Error> errors = validator.validate(company);
        assertEquals(errors.size(), 0);

    }
    @Test
    public void validate_regNrIsNullOrEmpty_error() {
        company.setRegNr(null);
        List<Error> errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "regNr");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        company.setRegNr(" ");
        errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "regNr");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_nameIsNullOrEmpty_error() {
        company.setName(null);
        List<Error> errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        company.setName(" ");
        errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_addressIsNullOrEmpty_error() {
        company.setAddress(null);
        List<Error> errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "address");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        company.setAddress(" ");
        errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "address");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_eMailIsNullOrEmpty_error() {
        company.setEMail(null);
        List<Error> errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "eMail");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        company.setEMail(" ");
        errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "eMail");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_pathFromAccountsIsNullOrEmpty_error() {
        company.setPathFromAccounts(null);
        List<Error> errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pathFromAccounts");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        company.setPathFromAccounts(" ");
        errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pathFromAccounts");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_pathToAccountsIsNullOrEmpty_error() {
        company.setPathToAccounts(null);
        List<Error> errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pathToAccounts");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        company.setPathToAccounts(" ");
        errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pathToAccounts");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_duplicateRegNr_error() {
        Company foundCompany = new Company(1, "regNr", "name", "address", "eMail",
                "pathFromAccounts", "pathToAccounts");
        Mockito.when(database.getCompanyByRegNr("regNr"))
                .thenReturn(Optional.of(foundCompany));
        List<Error> errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "regNr");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated");
    }

    @Test
    public void validate_noDuplicateRegNr_noErrors() {
        Mockito.when(database.getCompanyByRegNr("regNr"))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(company);
        assertEquals(errors.size(), 0);
    }

}