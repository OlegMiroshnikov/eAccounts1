package lv.javaguru.java2.businesslogic.company.removecompany;

import lv.javaguru.java2.dao.CompanyDaoInterface;
import lv.javaguru.java2.domens.Company;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class RemoveContractValidatorTest {
    private CompanyDaoInterface database;
    private RemoveCompanyValidator validator;
    private Company company;

    @Before
    public void init() {
        database = Mockito.mock(CompanyDaoInterface.class);
        validator = new RemoveCompanyValidator(database);
        company = new Company(1, "regNr", "name", "address", "eMail",
                "pathFromAccounts", "pathToAccounts");

    }

    @Test
    public void validate_idIsNull_error() {
        company.setId(null);
        List<Error> errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be null");
    }

    @Test
    public void validate_notExistCompanyById_error() {
        Mockito.when(database.getCompanyById(1))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(company);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Company by id not exist");
    }

    @Test
    public void validate_existCompanyById_noErrors() {
        Company foundCompany = new Company(1, "personalCode", "name", "address", "eMail",
                "pathFromAccounts", "pathToAccounts");
        Mockito.when(database.getCompanyById(1))
                .thenReturn(Optional.of(foundCompany));
        List<Error> errors = validator.validate(company);
        assertEquals(errors.size(), 0);
    }

}