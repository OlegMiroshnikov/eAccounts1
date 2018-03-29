package lv.javaguru.java2.businesslogic.company.removecompany;

import lv.javaguru.java2.dao.CompanyDaoInterface;
import lv.javaguru.java2.domens.Company;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RemoveCompanyServiceTest {
    private CompanyDaoInterface database;
    private RemoveCompanyValidator validator;
    private RemoveCompanyService service;
    private Company company;

    @Before
    public void init() {
        database = Mockito.mock(CompanyDaoInterface.class);
        validator = Mockito.mock(RemoveCompanyValidator.class);
        service = new RemoveCompanyService(database, validator);
        company = new Company(1, "regNr", "name", "address", "eMail",
                "pathFromAccounts", "pathToAccounts");
    }

    @Test
    public void removeCompany_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(company))
                .thenReturn(errors);
        RemoveCompanyResponse response = service.removeCompany(company);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void removeCompany_areErrors_fail() {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("id", "Must not be null"));
        Mockito.when(validator.validate(company))
                .thenReturn(errors);
        RemoveCompanyResponse response = service.removeCompany(company);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }

}
