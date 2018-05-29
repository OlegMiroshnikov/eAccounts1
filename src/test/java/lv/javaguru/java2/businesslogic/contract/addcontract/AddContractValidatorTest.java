package lv.javaguru.java2.businesslogic.contract.addcontract;

import lv.javaguru.java2.dao.ClientDao;
import lv.javaguru.java2.dao.CompanyDao;
import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Client;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.Contract;
import lv.javaguru.java2.validators.Error;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class AddContractValidatorTest {

    @Mock
    private ContractDao contractDao;

    @Mock
    private ClientDao clientDao;

    @Mock
    private CompanyDao companyDao;

    @InjectMocks
    private AddContractValidator validator;

    private AddContractRequest request;

    @Before
    public void init() {
        request = new AddContractRequest(1, 1, "number", new Date(), new Date(), new Date(),
                1, 10, 0);
        Company company = new Company();
        Client client = new Client();
        Mockito.when(contractDao.getContractByCompanyIdAndClientId(1, 1))
                .thenReturn(Optional.empty());
        Mockito.when(clientDao.getClientById(1))
                .thenReturn(Optional.of(client));
        Mockito.when(companyDao.getCompanyById(1))
                .thenReturn(Optional.of(company));
    }

    @Test
    public void validate_contractIsOK_noErrors() {
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void validate_companyIdIsNull_isError() {
        request.setCompanyId(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "companyId");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be null");
    }

    @Test
    public void validate_clientIdIsNull_isError() {
        request.setClientId(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "clientId");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be null");
    }

    @Test
    public void validate_numberIsNullOrEmpty_isError() {
        request.setNumber(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "number");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
        request.setNumber(" ");
        errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "number");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void validate_dateSignIsNull_isError() {
        request.setDateSign(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "dateSign");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be null");
    }

    @Test
    public void validate_dateBeginIsNull_isError() {
        request.setDateBegin(null);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "dateBegin");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be null");
    }

    @Test
    public void validate_duplicateContractBetweenCompanyAndClient_isError() {
        Contract foundContract = new Contract();
        Mockito.when(contractDao.getContractByCompanyIdAndClientId(1, 1))
                .thenReturn(Optional.of(foundContract));
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "");
        assertEquals(errors.get(0).getErrorMessage(), "The contract between the company and the client already exists");
    }

    @Test
    public void validate_noDuplicateContractBetweenCompanyAndClient_noErrors() {
        Mockito.when(contractDao.getContractByCompanyIdAndClientId(1, 1))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void validate_clientByIdNotExist_isError() {
        Mockito.when(clientDao.getClientById(1))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "contract");
        assertEquals(errors.get(0).getErrorMessage(), "Client must be exist");
    }

    @Test
    public void validate_companyByIdNotExist_isError() {
        Mockito.when(companyDao.getCompanyById(1))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "contract");
        assertEquals(errors.get(0).getErrorMessage(), "Company must be exist");
    }

}