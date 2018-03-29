package lv.javaguru.java2.businesslogic.contract.removecontract;

import lv.javaguru.java2.dao.ContractDaoInterface;
import lv.javaguru.java2.domens.Contract;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RemoveContractServiceTest {
    private ContractDaoInterface database;
    private RemoveContractValidator validator;
    private RemoveContractService service;
    private Contract contract;

    @Before
    public void init() {
        database = Mockito.mock(ContractDaoInterface.class);
        validator = Mockito.mock(RemoveContractValidator.class);
        service = new RemoveContractService(database, validator);
        contract = new Contract (1, 1, 1, "number", LocalDate.now(), LocalDate.now(), LocalDate.now(),
                1, 10, 0);
    }

    @Test
    public void removeContract_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(contract))
                .thenReturn(errors);
        RemoveContractResponse response = service.removeContract(contract);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void removeContract_areErrors_fail() {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("companyId", "Must not be null"));
        Mockito.when(validator.validate(contract))
                .thenReturn(errors);
        RemoveContractResponse response = service.removeContract(contract);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }


}