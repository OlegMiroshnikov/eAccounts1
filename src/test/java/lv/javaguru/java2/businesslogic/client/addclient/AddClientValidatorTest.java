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

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AddClientValidatorTest {

    @Mock
    private ClientDaoInterface database;

    @InjectMocks
    private AddClientValidator validator = new AddClientValidator();

    private Client client;

    @Before
    public void init() {
//        database = Mockito.mock(ClientDaoInterface.class);
//        validator = new AddClientValidator(database);
        client = new Client(1, "personalCode", "name", "address", "eMail");
    }

    @Test
    public void validate_clientIsOK_noErrors() {
        List<Error> errors = validator.validate(client);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void validate_personalCodeIsNullOrEmpty_error() {
        client.setPersonalCode(null);
        List<Error> errors = validator.validate(client);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personalCode");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        client.setPersonalCode(" ");
        errors = validator.validate(client);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personalCode");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_nameIsNullOrEmpty_error() {
        client.setName(null);
        List<Error> errors = validator.validate(client);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        client.setName(" ");
        errors = validator.validate(client);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_addressIsNullOrEmpty_error() {
        client.setAddress(null);
        List<Error> errors = validator.validate(client);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "address");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        client.setAddress(" ");
        errors = validator.validate(client);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "address");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_eMailIsNullOrEmpty_error() {
        client.setEMail(null);
        List<Error> errors = validator.validate(client);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "eMail");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        client.setEMail(" ");
        errors = validator.validate(client);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "eMail");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_duplicatePersonalCode_error() {
        Client foundClient = new Client(1, "personalCode", "name", "address", "eMail");
        Mockito.when(database.getClientByPersonalCode("personalCode"))
                .thenReturn(Optional.of(foundClient));
        List<Error> errors = validator.validate(client);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personalCode");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated");
    }

    @Test
    public void validate_noDuplicatePersonalCode_noErrors() {
        Mockito.when(database.getClientByPersonalCode("personalCode"))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(client);
        assertEquals(errors.size(), 0);
    }


}