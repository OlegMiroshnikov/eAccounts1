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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AddCompanyServiceTest {

    @Mock
    private CompanyDao companyDao;

    @Mock
    private AddCompanyValidator validator;

    @InjectMocks
    private AddCompanyService service;

    private AddCompanyRequest request;

    @Before
    public void init() {
        request = new AddCompanyRequest("regNr", "name", "address", "eMail",
                "pathFromAccounts", "pathToAccounts");

    }

    @Test
    public void addCompany_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        AddCompanyResponse response = service.addCompany(request);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void addCompany_areErrors_fail() {
        request.setRegNr(null);
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("regNr", "Must not be empty"));
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        AddCompanyResponse response = service.addCompany(request);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }

}
