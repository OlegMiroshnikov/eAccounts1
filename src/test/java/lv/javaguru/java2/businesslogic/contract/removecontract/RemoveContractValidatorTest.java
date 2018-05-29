package lv.javaguru.java2.businesslogic.contract.removecontract;

import lv.javaguru.java2.dao.AccountDao;
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
public class RemoveContractValidatorTest {

    @Mock
    private ContractDao contractDaoImpl;

    @Mock
    private AccountDao accountDaoImpl;

    @InjectMocks
    private RemoveContractValidator validator;

    private Contract contract;

    private RemoveContractRequest request;

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
        request = new RemoveContractRequest(1);
        Mockito.when(contractDaoImpl.getContractById(1))
                .thenReturn(Optional.of(contract));
        Mockito.when(accountDaoImpl.getAccountListByContractId(1))
                .thenReturn(new ArrayList<Account>());
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

    @Test
    public void validate_isExistAnyAccountByContractId_isError() {
        List<Account> accounts = new ArrayList();
        accounts.add(new Account());
        Mockito.when(accountDaoImpl.getAccountListByContractId(1))
                .thenReturn(accounts);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "There are also accounts with this contract");
    }



}