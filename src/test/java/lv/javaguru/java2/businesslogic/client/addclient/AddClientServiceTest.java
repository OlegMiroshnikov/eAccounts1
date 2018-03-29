package lv.javaguru.java2.businesslogic.client.addclient;

import lv.javaguru.java2.dao.ClientDaoInterface;
import lv.javaguru.java2.domens.Client;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
@RunWith(MockitoJUnitRunner.class)
public class AddClientServiceTest {
    @Mock
    private ClientDaoInterface database;

    @Mock
    private AddClientValidator validator;

    @InjectMocks
    private AddClientService service = new AddClientService();
//    private AddClientService service;
    private Client client;

    @Before
    public void init() {
//        database = Mockito.mock(ClientDaoInterface.class);
//        validator = Mockito.mock(AddClientValidator.class);
//        service = new AddClientService(database, validator);
        client = new Client(1, "personalCode", "name", "address", "eMail");
    }

    @Test
    public void addClient_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(client))
                .thenReturn(errors);
        AddClientResponse response = service.addClient(client);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void addClient_areErrors_fail() {
        client.setPersonalCode(null);
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("personalCode", "Must not be empty"));
        Mockito.when(validator.validate(client))
                .thenReturn(errors);
        AddClientResponse response = service.addClient(client);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }

}


