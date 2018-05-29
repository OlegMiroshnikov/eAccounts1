package lv.javaguru.java2.businesslogic.company.removecompany;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class RemoveCompanyServiceTest {

    @Mock
    private CompanyDao companyDao;

    @Mock
    private RemoveCompanyValidator validator;

    @InjectMocks
    private RemoveCompanyService service;

    private RemoveCompanyRequest request;

    @Before
    public void init() {
        request = new RemoveCompanyRequest(1);
    }

    @Test
    public void removeCompany_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        RemoveCompanyResponse response = service.removeCompany(request);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void removeCompany_areErrors_fail() {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("id", "Must not be null"));
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        RemoveCompanyResponse response = service.removeCompany(request);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }

}
