package lv.javaguru.java2.businesslogic.company.addcompany;

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

public class AddCompanyServiceTest {
    private CompanyDaoInterface database;
    private AddCompanyValidator validator;
    private AddCompanyService service;
    private Company company;

    @Before
    public void init() {
        database = Mockito.mock(CompanyDaoInterface.class);
        validator = Mockito.mock(AddCompanyValidator.class);
        service = new AddCompanyService(database, validator);
        company = new Company(1, "regNr", "name", "address", "eMail",
                "pathFromAccounts", "pathToAccounts");
    }

    @Test
    public void addCompany_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(company))
                .thenReturn(errors);
        AddCompanyResponse response = service.addCompany(company);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void addCompany_areErrors_fail() {
        company.setRegNr(null);
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("regNr", "Must not be empty"));
        Mockito.when(validator.validate(company))
                .thenReturn(errors);
        AddCompanyResponse response = service.addCompany(company);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }

}
