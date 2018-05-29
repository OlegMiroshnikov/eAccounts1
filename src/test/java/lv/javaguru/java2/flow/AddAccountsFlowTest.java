package lv.javaguru.java2.flow;

import lv.javaguru.java2.businesslogic.account.addaccount.AddAccountRequest;
import lv.javaguru.java2.businesslogic.account.addaccount.AddAccountResponse;
import lv.javaguru.java2.businesslogic.account.addaccount.AddAccountService;
import lv.javaguru.java2.businesslogic.account.getaccountlist.GetAccountListService;
import lv.javaguru.java2.businesslogic.client.addclient.AddClientRequest;
import lv.javaguru.java2.businesslogic.client.addclient.AddClientResponse;
import lv.javaguru.java2.businesslogic.client.addclient.AddClientService;
import lv.javaguru.java2.businesslogic.client.getclientlist.GetClientListService;
import lv.javaguru.java2.businesslogic.company.addcompany.AddCompanyRequest;
import lv.javaguru.java2.businesslogic.company.addcompany.AddCompanyResponse;
import lv.javaguru.java2.businesslogic.company.addcompany.AddCompanyService;
import lv.javaguru.java2.businesslogic.company.getcompanylist.GetCompanyListService;
import lv.javaguru.java2.businesslogic.contract.addcontract.AddContractRequest;
import lv.javaguru.java2.businesslogic.contract.addcontract.AddContractResponse;
import lv.javaguru.java2.businesslogic.contract.addcontract.AddContractService;
import lv.javaguru.java2.businesslogic.contract.getcontractlist.GetContractListService;
import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.dao.AccountDao;
import lv.javaguru.java2.dao.ClientDao;
import lv.javaguru.java2.dao.CompanyDao;
import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Account;
import lv.javaguru.java2.domain.Client;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.Contract;
import org.hamcrest.core.Is;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

@SqlGroup({
        @Sql("/mysql-scripts/create-database,sql"),
})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
@Transactional
@Commit
public class AddAccountsFlowTest {

    @Autowired private ClientDao clientDao;
    @Autowired private CompanyDao companyDao;
    @Autowired private ContractDao contractDao;
    @Autowired private AccountDao accountDao;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private SessionFactory sessionFactory;

    @Autowired private AddClientService addClientService;
    @Autowired private AddCompanyService addCompanyService;
    @Autowired private AddContractService addContractService;
    @Autowired private AddAccountService addAccountService;

    @Autowired private GetClientListService getClientListService;
    @Autowired private GetCompanyListService getCompanyListService;
    @Autowired private GetContractListService getContractListService;
    @Autowired private GetAccountListService getAccountListService;

    private Contract contract;

    @Before
    public void setUp() {
        contract = new Contract(1, 1, "number", new Date(), new Date(), new Date(), 10, 10, 0);
    }

    @Test
    public void addContractsAndAccountsToSomeClientsAndCompanies_areAdded() {
        AddClientRequest addClientRequest1 = new AddClientRequest("personalCode1",
                "name1", "address1", "eMail1");
        AddClientResponse addClientResponse1 = addClientService.addClient(addClientRequest1);

        AddCompanyRequest addCompanyRequest1 = new AddCompanyRequest("regNr1",
                "name1", "address1" , "eMail1", "src\\test\\resources\\accounts\\pathFromAccounts", "src\\test\\resources\\accounts\\pathToAccounts");
        AddCompanyResponse addCompanyResponse1 = addCompanyService.addCompany(addCompanyRequest1);

        AddContractRequest addContractRequest1 = new AddContractRequest(addCompanyResponse1.getId(), addClientResponse1.getId(),
                "number1", new Date(), new Date(), null, 5, 10, 0);
        AddContractResponse addContractResponse1 = addContractService.addContract(addContractRequest1);

        AddAccountRequest addAccountRequest1 = new AddAccountRequest(addContractResponse1.getId(), "testAccount");
        AddAccountResponse addAccountResponse1 = addAccountService.addAccount(addAccountRequest1);

        assertThat(addClientResponse1.isSuccess(), is(true));
        assertThat(addCompanyResponse1.isSuccess(), is(true));
        assertThat(addContractResponse1.isSuccess(), is(true));
        assertThat(addAccountResponse1.isSuccess(), is(true) );

        AddClientRequest addClientRequest2 = new AddClientRequest("personalCode2",
                "name2", "address2", "eMail2");
        AddClientResponse addClientResponse2 = addClientService.addClient(addClientRequest2);

        AddCompanyRequest addCompanyRequest2 = new AddCompanyRequest("regNr2",
                "name2", "address2" , "eMail2", "src\\test\\resources\\accounts\\pathFromAccounts", "src\\test\\resources\\accounts\\pathToAccounts");
        AddCompanyResponse addCompanyResponse2 = addCompanyService.addCompany(addCompanyRequest2);

        AddContractRequest addContractRequest2 = new AddContractRequest(addCompanyResponse2.getId(), addClientResponse2.getId(),
                "number2", new Date(), new Date(), null, 5, 10, 0);
        AddContractResponse addContractResponse2 = addContractService.addContract(addContractRequest2);

        AddAccountRequest addAccountRequest2 = new AddAccountRequest(addContractResponse2.getId(), "testAccount");
        AddAccountResponse addAccountResponse2 = addAccountService.addAccount(addAccountRequest2);

        assertThat(addClientResponse2.isSuccess(), is(true));
        assertThat(addCompanyResponse2.isSuccess(), is(true));
        assertThat(addContractResponse2.isSuccess(), is(true));
        assertThat(addAccountResponse2.isSuccess(), is(true) );

        List<Client> clients = getClientListService.getClientList();
        assertThat(clients.size(), is(2));
        List<Company> companies = getCompanyListService.getCompanyList();
        assertThat(companies.size(), is(2));
        List<Contract> contracts = getContractListService.getContractList();
        assertThat(contracts.size(), is(2));
        List<Account> accounts = getAccountListService.getAccountList();
        assertThat(accounts.size(), is(2));

    }

}
