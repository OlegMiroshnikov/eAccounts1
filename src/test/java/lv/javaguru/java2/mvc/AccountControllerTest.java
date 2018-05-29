package lv.javaguru.java2.mvc;

import lv.javaguru.java2.businesslogic.account.getaccountlist.GetAccountListService;
import lv.javaguru.java2.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;

import static lv.javaguru.java2.domain.builders.AccountBuilder.createAccount;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
    @InjectMocks
    private AccountController controller;
    @Mock
    private GetAccountListService getAccountListService;

    @Test
    public void listAccounts_returnAccountsPage_OK() {
        ArrayList<Account> listAccounts = new ArrayList<>();
        Account account = createAccount()
                .withId(1L)
                .withContractId(1)
                .withFileName("testAccount")
                .build();
        listAccounts.add(account);
        Mockito.when(getAccountListService.getAccountList())
                .thenReturn(listAccounts);
        ModelMap model = new ModelMap();
        String viewName = controller.listAccounts(model);
        assertEquals("/accounts", viewName);
        assertSame(listAccounts, model.get("listAccounts"));
    }


}