package lv.javaguru.java2.businesslogic.company.removecompany;

import lv.javaguru.java2.dao.CompanyDao;
import lv.javaguru.java2.dao.ContractDao;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static lv.javaguru.java2.domain.builders.CompanyBuilder.createCompany;

@RunWith(MockitoJUnitRunner.class)
public class RemoveCompanytValidatorTest {

    @Mock
    private CompanyDao companyDao;

    @Mock
    private ContractDao contractDao;

    @InjectMocks
    private RemoveCompanyValidator validator;

    private Company company;

    private RemoveCompanyRequest request;

    @Before
    public void init() {
        company = createCompany()
                .withId(1)
                .withRegNr("regNr")
                .withName("name")
                .withAddress("address")
                .withEMail("eMail")
                .withPathFromAccounts("pathFromAccounts")
                .withPathToAccounts("pathToAccounts")
                .build();
        request = new RemoveCompanyRequest(1);
        Mockito.when(companyDao.getCompanyById(1))
                .thenReturn(Optional.of(company));
        Mockito.when(contractDao.getContractListByCompanyId(1))
                .thenReturn(new ArrayList<Contract>());
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
    public void validate_notExistCompanyById_isError() {
        Mockito.when(companyDao.getCompanyById(1))
                .thenReturn(Optional.empty());
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Company by id not exist");
    }

    @Test
    public void validate_existCompanyById_noErrors() {
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void validate_isExistAnyContractsByCompanyId_isError() {
        List<Contract> contracts = new ArrayList();
        contracts.add(new Contract());
        Mockito.when(contractDao.getContractListByCompanyId(1))
                .thenReturn(contracts);
        List<Error> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "There are also contracts with this company");
    }

}