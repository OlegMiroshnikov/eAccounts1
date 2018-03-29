package lv.javaguru.java2.businesslogic.client.removeclient;

import lv.javaguru.java2.dao.ClientDaoInterface;
import lv.javaguru.java2.domens.Client;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RemoveClientServiceTest {

    private ClientDaoInterface database;
    private RemoveClientValidator validator;
    private RemoveClientService service;
    private Client client;

    @Before
    public void init() {
        database = Mockito.mock(ClientDaoInterface.class);
        validator = Mockito.mock(RemoveClientValidator.class);
        service = new RemoveClientService(database, validator);
        client = new Client(1, "personalCode", "name", "address", "eMail");
    }

    @Test
    public void removeClient_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(client))
                .thenReturn(errors);
        RemoveClientResponse response = service.removeClient(client);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void removeClient_areErrors_fail() {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("id", "Must not be null"));
        Mockito.when(validator.validate(client))
                .thenReturn(errors);
        RemoveClientResponse response = service.removeClient(client);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }

}
