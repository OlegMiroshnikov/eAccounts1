package lv.javaguru.java2.businesslogic.client.addclient;

import lv.javaguru.java2.dao.ClientDao;
import lv.javaguru.java2.domain.Client;
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
import static lv.javaguru.java2.domain.builders.ClientBuilder.createClient;

@RunWith(MockitoJUnitRunner.class)
public class AddClientValidatorTest {

    @Mock
    private ClientDao clientDao;

    @InjectMocks
    private AddClientValidator validator;

    private AddClientRequest request;

    @Before
    public void init() {
//        clientDao = Mockito.mock(ClientDao.class);
//        validator = new AddClientValidator(clientDao);
        request = new AddClientRequest("personalCode", "name", "address", "eMail");
    }


    @Test
    public void validate_clientIsOK_noErrors() {
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void validate_personalCodeIsNullOrEmpty_isError() {
        request.setPersonalCode(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personalCode");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        request.setPersonalCode(" ");
        errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personalCode");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_nameIsNullOrEmpty_isError() {
        request.setName(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        request.setName(" ");
        errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_eMailIsNullOrEmpty_isError() {
        request.setEMail(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "eMail");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        request.setEMail(" ");
        errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "eMail");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_duplicatePersonalCode_isError() {
        Client foundClient = createClient()
                .withId(1)
                .withPersonalCode("personalCode")
                .withName("name")
                .withAddress("address")
                .withEMail("eMail")
                .build();
        Mockito.when(clientDao.getClientByPersonalCode("personalCode"))
                .thenReturn(Optional.of(foundClient));
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personalCode");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated");
    }

    @Test
    public void validate_noDuplicatePersonalCode_noErrors() {
        Mockito.when(clientDao.getClientByPersonalCode("personalCode"))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

}