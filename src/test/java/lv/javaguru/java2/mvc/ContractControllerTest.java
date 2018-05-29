package lv.javaguru.java2.mvc;

import lv.javaguru.java2.businesslogic.company.getcompanylist.GetCompanyListService;
import lv.javaguru.java2.businesslogic.contract.getcontract.GetContractService;
import lv.javaguru.java2.businesslogic.contract.getcontractlist.GetContractListService;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.Contract;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Date;

import static lv.javaguru.java2.domain.builders.ContractBuilder.createContract;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ContractControllerTest {
    @InjectMocks
    private ContractController controller;
    @Mock
    private GetContractListService getContractListService;

    @Test
    public void listContracts_returnContractsPage_OK() {
        ArrayList<Contract> listContracts = new ArrayList<>();
        Contract contract = createContract()
                .withId(1)
                .withCompanyId(1)
                .withClientId(1)
                .withNumber("number")
                .withDateSign(new Date())
                .withDateBegin(new Date())
                .withDateEnd(new Date())
                .withDayToSendAccount(1)
                .withCountDaysToSendReminder(10)
                .withStatus(0)
                .build();
        listContracts.add(contract);
        Mockito.when(getContractListService.getContractList())
                .thenReturn(listContracts);
        ModelMap model = new ModelMap();
        String viewName = controller.listContracts(model);
        assertEquals("/contracts", viewName);
        assertSame(listContracts, model.get("listContracts"));
    }


}