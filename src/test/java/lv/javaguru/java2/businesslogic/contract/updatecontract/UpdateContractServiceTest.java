package lv.javaguru.java2.businesslogic.contract.updatecontract;

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
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateContractServiceTest {
    @Mock
    private ContractDao contractDao;
    @Mock
    private UpdateContractValidator validator;
    @InjectMocks
    private UpdateContractService service;

    private UpdateContractRequest request;

    @Before
    public void init() {
        request = new UpdateContractRequest(1, 1, 1, "newNumber",
                new Date(), new Date(), new Date(), 1,10,0 );
    }

    @Test
    public void updateContract_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        UpdateContractResponse response = service.updateContract(request);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void updateContract_areErrors_fail() {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("id", "Must not be null"));
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        UpdateContractResponse response = service.updateContract(request);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }


}