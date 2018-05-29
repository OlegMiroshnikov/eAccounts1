package lv.javaguru.java2.businesslogic.client.removeclient;

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

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RemoveClientServiceTest {

    @Mock
    private ClientDao clientDao;

    @Mock
    private RemoveClientValidator validator;
    @InjectMocks
    private RemoveClientService service;

    private RemoveClientRequest request;

    @Before
    public void init() {
        request = new RemoveClientRequest(1);
    }

    @Test
    public void removeClient_noErrors_success() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        RemoveClientResponse response = service.removeClient(request);
        assertTrue(response.isSuccess());
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void removeClient_areErrors_fail() {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("id", "Must not be null"));
        Mockito.when(validator.validate(request))
                .thenReturn(errors);
        RemoveClientResponse response = service.removeClient(request);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrors(), errors);
    }

}
