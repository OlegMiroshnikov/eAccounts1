package lv.javaguru.java2.businesslogic.client.removeclient;

import lv.javaguru.java2.dao.ClientDaoInterface;
import lv.javaguru.java2.domens.Client;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class RemoveClientValidatorTest {
    private ClientDaoInterface database;
    private RemoveClientValidator validator;
    private Client client;

    @Before
    public void init() {
        database = Mockito.mock(ClientDaoInterface.class);
        validator = new RemoveClientValidator(database);
        client = new Client(1, "personalCode", "name", "address", "eMail");
    }

    @Test
    public void validate_idIsNull_error() {
        client.setId(null);
        List<Error> errors = validator.validate(client);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be null");
    }

    @Test
    public void validate_notExistClientById_error() {
        Mockito.when(database.getClientById(1))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(client);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Client by id not exist");
    }

    @Test
    public void validate_existClientById_noErrors() {
        Client foundClient = new Client(1, "personalCode", "name", "address", "eMail");
        Mockito.when(database.getClientById(1))
                .thenReturn(Optional.of(foundClient));
        List<Error> errors = validator.validate(client);
        assertEquals(errors.size(), 0);
    }

}