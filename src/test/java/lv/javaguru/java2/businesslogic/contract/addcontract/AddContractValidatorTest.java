package lv.javaguru.java2.businesslogic.contract.addcontract;

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

public class AddContractValidatorTest {
    private ContractDaoInterface database;
    private AddContractValidator validator;
    private Contract contract;

    @Before
    public void init() {
        database = Mockito.mock(ContractDaoInterface.class);
        validator = new AddContractValidator(database);
        contract = new Contract (1, 1, 1, "number", LocalDate.now(), LocalDate.now(), LocalDate.now(),
                1, 10, 0);
    }

    @Test
    public void validate_contractIsOK_noErrors() {
        List<Error> errors = validator.validate(contract);
        assertEquals(errors.size(), 0);
    }
    @Test
    public void validate_companyIdIsNull_error() {
        contract.setCompanyId(null);
        List<Error> errors = validator.validate(contract);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "companyId");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be null");
    }

    @Test
    public void validate_clientIdIsNull_error() {
        contract.setClientId(null);
        List<Error> errors = validator.validate(contract);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "clientId");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be null");
    }

    @Test
    public void validate_duplicateContractBetweenCompanyAndClient_error() {
        Contract foundContract = new Contract();
        foundContract.setId(1);
        foundContract.setCompanyId(1);
        foundContract.setClientId(1);
        Mockito.when(database.getContractByCompanyIdAndClientId(1, 1))
                .thenReturn(Optional.of(foundContract));
        List<Error> errors = validator.validate(contract);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "");
        assertEquals(errors.get(0).getErrorMessage(), "The contract between the company and the customer already exists");
    }

    @Test
    public void validate_noDuplicateContractBetweenCompanyAndClient_noErrors() {
        Mockito.when(database.getContractByCompanyIdAndClientId(1,1))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(contract);
        assertEquals(errors.size(), 0);
    }


}