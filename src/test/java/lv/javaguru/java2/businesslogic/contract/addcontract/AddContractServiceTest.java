package lv.javaguru.java2.businesslogic.contract.addcontract;

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

public class AddContractServiceTest {
    private ContractDaoInterface database;
    private AddContractValidator validator;
    private AddContractService service;
    private Contract contract;

    @Before
    public void init() {
        database = Mockito.mock(ContractDaoInterface.class);
        validator = Mockito.mock(AddContractValidator.class);
        service = new AddContractService(database, validator);
        contract = new Contract (1, 1, 1, "number", LocalDate.now(), LocalDate.now(), LocalDate.now(),
                1, 10, 0);
    }

    @Test
    public void addContract_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(contract))
                .thenReturn(errors);
        AddContractResponse response = service.addContract(contract);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void addContract_areErrors_fail() {
        contract.setCompanyId(null);
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("companyId", "Must not be null"));
        Mockito.when(validator.validate(contract))
                .thenReturn(errors);
        AddContractResponse response = service.addContract(contract);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }

}