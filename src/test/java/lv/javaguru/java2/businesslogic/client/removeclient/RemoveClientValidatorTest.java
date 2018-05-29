package lv.javaguru.java2.businesslogic.client.removeclient;

import lv.javaguru.java2.dao.ClientDao;
import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Client;
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
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

import static lv.javaguru.java2.domain.builders.ClientBuilder.createClient;

@RunWith(MockitoJUnitRunner.class)
public class RemoveClientValidatorTest {

    @Mock
    private ClientDao clientDao;

    @Mock
    private ContractDao contractDao;

    @InjectMocks
    private RemoveClientValidator validator;

    private Client client;
    private RemoveClientRequest request;

    @Before
    public void init() {
        client = createClient()
                .withId(1)
                .withPersonalCode("personalCode")
                .withName("name")
                .withAddress("address")
                .withEMail("eMail")
                .build();
        request = new RemoveClientRequest(1);
        Mockito.when(clientDao.getClientById(1))
                .thenReturn(Optional.of(client));
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
    public void validate_notExistClientById_isError() {
        Mockito.when(clientDao.getClientById(1))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Client by id not exist");
    }

    @Test
    public void validate_existClientById_noErrors() {
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void validate_isExistAnyContractsByClientId_isError() {
        List<Contract> contracts = new ArrayList();
        contracts.add(new Contract());
        Mockito.when(contractDao.getContractListByClientId(1))
                .thenReturn(contracts);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "There are also contracts with this client");
    }

}