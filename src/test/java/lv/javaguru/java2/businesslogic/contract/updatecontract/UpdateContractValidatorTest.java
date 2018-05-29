package lv.javaguru.java2.businesslogic.contract.updatecontract;

import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Account;
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
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static lv.javaguru.java2.domain.builders.ContractBuilder.createContract;

@RunWith(MockitoJUnitRunner.class)
public class UpdateContractValidatorTest {

    @Mock
    private ContractDao contractDaoImpl;

    @InjectMocks
    private UpdateContractValidator validator;

    private Contract contract;

    private UpdateContractRequest request;

    @Before
    public void init() {
        contract = createContract()
                .withId(1)
                .withCompanyId(1)
                .withClientId(1)
                .withNumber("number")
                .withDateSign(new Date())
                .withDateBegin(new Date())
                .withDateEnd(new Date())
                .withDayToSendAccount(1)
                .withCountDaysToSendReminder(10)
                .withStatus(0)
                .build();
        request = new UpdateContractRequest(1, 1, 1, "newNumber",
                new Date(), new Date(), new Date(), 1,10,0 );
        Mockito.when(contractDaoImpl.getContractById(1))
                .thenReturn(Optional.of(contract));
    }

    @Test
    public void validate_idIsNull_isError() {
        request.setId(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be null");
    }

    @Test
    public void validate_notExistContractById_isError() {
        Mockito.when(contractDaoImpl.getContractById(1))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Contract by id not exist");
    }

    @Test
    public void validate_existContractById_noErrors() {
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

}