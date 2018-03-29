package lv.javaguru.java2.businesslogic.contract.removecontract;

import lv.javaguru.java2.dao.ContractDaoInterface;
import lv.javaguru.java2.domens.Contract;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class RemoveContractValidatorTest {
    private ContractDaoInterface database;
    private RemoveContractValidator validator;
    private Contract contract;

    @Before
    public void init() {
        database = Mockito.mock(ContractDaoInterface.class);
        validator = new RemoveContractValidator(database);
        contract = new Contract (1, 1, 1, "number", LocalDate.now(), LocalDate.now(), LocalDate.now(),
                1, 10, 0);
    }

    @Test
    public void validate_idIsNull_error() {
        contract.setId(null);
        List<Error> errors = validator.validate(contract);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be null");
    }

    @Test
    public void validate_notExistContractById_error() {
        Mockito.when(database.getContractById(1))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(contract);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Contract by id not exist");
    }

    @Test
    public void validate_existContractById_noErrors() {
        Contract foundContract = new Contract (1, 1, 1, "number", LocalDate.now(), LocalDate.now(), LocalDate.now(),
                1, 10, 0);
        Mockito.when(database.getContractById(1))
                .thenReturn(Optional.of(foundContract));
        List<Error> errors = validator.validate(contract);
        assertEquals(errors.size(), 0);
    }


}