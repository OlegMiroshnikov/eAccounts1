package lv.javaguru.java2.businesslogic.company.updatecompany;

import lv.javaguru.java2.dao.CompanyDao;
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
public class UpdateCompanyServiceTest {
    @Mock
    private CompanyDao companyDao;
    @Mock
    private UpdateCompanyValidator validator;
    @InjectMocks
    private UpdateCompanyService service;

    private UpdateCompanyRequest request;

    @Before
    public void init() {
        request = new UpdateCompanyRequest(1, "newRegNr",
                "newName", "newAddress", "newEMail",
                "NewPathFromAccounts", "newPathToAccounts");
    }

    @Test
    public void updateCompany_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        UpdateCompanyResponse response = service.updateCompany(request);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void updateCompany_areErrors_fail() {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("id", "Must not be null"));
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        UpdateCompanyResponse response = service.updateCompany(request);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }

}