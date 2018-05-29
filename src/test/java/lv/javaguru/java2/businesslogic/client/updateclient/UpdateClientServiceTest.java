package lv.javaguru.java2.businesslogic.client.updateclient;

import lv.javaguru.java2.dao.ClientDao;
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

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class UpdateClientServiceTest {

    @Mock
    private ClientDao clientDao;
    @Mock
    private UpdateClientValidator validator;
    @InjectMocks
    private UpdateClientService service;

    private UpdateClientRequest request;

    @Before
    public void init() {
        request = new UpdateClientRequest(1, "newPersonalCode", "newName", "newAddress", "newEMail");
    }

    @Test
    public void updateClient_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        UpdateClientResponse response = service.updateClient(request);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void updateClient_areErrors_fail() {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("id", "Must not be null"));
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        UpdateClientResponse response = service.updateClient(request);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }

}