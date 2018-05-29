package lv.javaguru.java2.businesslogic.contract.removecontract;

import lv.javaguru.java2.dao.ContractDao;
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
public class RemoveContractServiceTest {

    @Mock
    private ContractDao contractDao;

    @Mock
    private RemoveContractValidator validator;

    @InjectMocks
    private RemoveContractService service;

    private RemoveContractRequest request;

    @Before
    public void init() {
        request = new RemoveContractRequest(1);
    }

    @Test
    public void removeContract_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        RemoveContractResponse response = service.removeContract(request);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void removeContract_areErrors_fail() {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("companyId", "Must not be null"));
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        RemoveContractResponse response = service.removeContract(request);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }


}