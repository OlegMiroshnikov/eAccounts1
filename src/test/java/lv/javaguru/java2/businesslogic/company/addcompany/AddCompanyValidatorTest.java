package lv.javaguru.java2.businesslogic.company.addcompany;

import lv.javaguru.java2.dao.CompanyDao;
import lv.javaguru.java2.domain.Company;
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
import static lv.javaguru.java2.domain.builders.CompanyBuilder.createCompany;

@RunWith(MockitoJUnitRunner.class)
public class AddCompanyValidatorTest {

    @Mock
    private CompanyDao companyDao;

    @InjectMocks
    private AddCompanyValidator validator;

    private AddCompanyRequest request;

    @Before
    public void init() {
        request = new AddCompanyRequest("regNr", "name", "address", "eMail",
                "pathFromAccounts", "pathToAccounts");
    }

    @Test
    public void validate_companyIsOK_noErrors() {
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 0);

    }
    @Test
    public void validate_regNrIsNullOrEmpty_isError() {
        request.setRegNr(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "regNr");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        request.setRegNr(" ");
        errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "regNr");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_nameIsNullOrEmpty_isError() {
        request.setName(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        request.setName(" ");
        errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_eMailIsNullOrEmpty_isError() {
        request.setEMail(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "eMail");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        request.setEMail(" ");
        errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "eMail");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_pathFromAccountsIsNullOrEmpty_isError() {
        request.setPathFromAccounts(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pathFromAccounts");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        request.setPathFromAccounts(" ");
        errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pathFromAccounts");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_pathToAccountsIsNullOrEmpty_isError() {
        request.setPathToAccounts(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pathToAccounts");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        request.setPathToAccounts(" ");
        errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pathToAccounts");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_duplicateRegNr_isError() {
        Company foundCompany = createCompany()
                .withId(1)
                .withRegNr("regNr")
                .withName("name")
                .withAddress("address")
                .withEMail("eMail")
                .withPathFromAccounts("pathFromAccounts")
                .withPathToAccounts("pathToAccounts")
                .build();
        Mockito.when(companyDao.getCompanyByRegNr("regNr"))
                .thenReturn(Optional.of(foundCompany));
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "regNr");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated");
    }

    @Test
    public void validate_noDuplicateRegNr_noErrors() {
        Mockito.when(companyDao.getCompanyByRegNr("regNr"))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }


}