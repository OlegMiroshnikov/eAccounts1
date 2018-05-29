package lv.javaguru.java2.businesslogic.contract.addcontract;

import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Contract;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class AddContractServiceTest {

    @Mock
    private ContractDao contractDao;

    @Mock
    private AddContractValidator validator;

    @InjectMocks
    private AddContractService service;

    private AddContractRequest request;

    @Before
    public void init() {
        request = new AddContractRequest ( 1, 1, "number", new Date(), new Date(), new Date(),
                1, 10, 0);

    }

    @Test
    public void addContract_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        AddContractResponse response = service.addContract(request);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void addContract_areErrors_fail() {
        request.setCompanyId(null);
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("companyId", "Must not be null"));
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        AddContractResponse response = service.addContract(request);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }

}