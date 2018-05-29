package lv.javaguru.java2.mvc;

import lv.javaguru.java2.businesslogic.company.getcompanylist.GetCompanyListService;
import lv.javaguru.java2.domain.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;

import static lv.javaguru.java2.domain.builders.CompanyBuilder.createCompany;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CompanyControllerTest {
    @InjectMocks
    private CompanyController controller;
    @Mock
    private GetCompanyListService getCompanyListService;

    @Test
    public void listCompanies_returnCompaniesPage_OK() {
        ArrayList<Company> listCompanies = new ArrayList<>();
        Company company = createCompany()
                .withId(1)
                .withRegNr("regNr")
                .withName("name")
                .withAddress("address")
                .withEMail("eMail")
                .withPathFromAccounts("pathFromAccounts")
                .withPathToAccounts("pathToAccounts")
                .build();
        listCompanies.add(company);
        Mockito.when(getCompanyListService.getCompanyList())
                .thenReturn(listCompanies);
        ModelMap model = new ModelMap();
        String viewName = controller.listCompanies(model);
        assertEquals("/companies", viewName);
        assertSame(listCompanies, model.get("listCompanies"));
    }

}

