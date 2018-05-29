package lv.javaguru.java2.dao;

import static org.junit.Assert.*;

import lv.javaguru.java2.configs.SpringAppConfig;
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

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SqlGroup({
        @Sql("/mysql-scripts/create-database,sql"),
        @Sql("/mysql-scripts/clients-data.sql"),
        @Sql("/mysql-scripts/companies-data.sql"),
        @Sql("/mysql-scripts/contracts-data.sql"),
        @Sql("/mysql-scripts/accounts-data.sql"),
})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
@Transactional
@Commit
public class ContractDaoImplTest {

    @Autowired
    private ContractDao contractDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    private Contract contract;

    @Before
    public void setUp() {
        contract = new Contract(1, 1, "number", new Date(), new Date(), new Date(), 10, 10, 0);
    }

    @Test
    public void addContract_contractNotNull_isAdded() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "contracts");
        assertNull(contract.getId());
        contractDao.addContract(contract);
        assertNotNull(contract.getId());
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "number = 'number'");
        Assert.assertThat(1, Is.is(expected));
    }

    @Test
    public void updateContract_contractNotNull_isUpdated() {
        contract.setId(1);
        contractDao.updateContract(contract);
        sessionFactory.getCurrentSession().flush();
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "number = 'number' and id = 1");
        Assert.assertThat(1, Is.is(expected));
    }


    @Test
    public void getContractById_contractWithIdIsExist_isFound() {
        Optional<Contract> contract = contractDao.getContractById(1);
        assertNotNull(contract);
        assertNotNull(contract.get().getId());
        assertEquals((Integer) 1, contract.get().getId());
        assertEquals("221/sft", contract.get().getNumber());
        assertEquals((Integer) 1, contract.get().getClient().getId());
        assertEquals((Integer) 1, contract.get().getCompany().getId());
        assertEquals(1, contract.get().getAccounts().size());
    }

    @Test
    public void getContractById_contractWithIdNotExist_notFound() {
        Optional<Contract> contract = contractDao.getContractById(777);
        assertEquals(Optional.empty(), contract);
    }

    @Test
    public void getContractById_contractWithNullDateFields_isReceived() {
        Optional<Contract> contract = contractDao.getContractById(3);
        assertNotNull(contract);
        assertNotNull(contract.get().getId());
        assertEquals((Integer) 3, contract.get().getId());
        assertNull(contract.get().getDateEnd());
    }

    @Test
    public void getContractByCompanyIdAndClientId_contractIsExist_isFound() {
        Optional<Contract> contract = contractDao.getContractByCompanyIdAndClientId(1, 1);
        assertNotNull(contract);
        assertNotNull(contract.get().getId());
        assertEquals((Integer) 1, contract.get().getId());
        assertEquals("221/sft", contract.get().getNumber());
    }

    @Test
    public void getContractByCompanyIdAndClientId_contractNotExist_notFound() {
        Optional<Contract> contract = contractDao.getContractByCompanyIdAndClientId(777, 777);
        assertEquals(Optional.empty(), contract);
    }

    @Test
    public void getContractByCompanyIdAndClientId_contractWithNullDateFields_isReceived() {
        Optional<Contract> contract = contractDao.getContractByCompanyIdAndClientId(3, 3);
        assertNotNull(contract);
        assertNotNull(contract.get().getId());
        assertEquals((Integer) 3, contract.get().getId());
        assertNull(contract.get().getDateEnd());
    }

    @Test
    public void removeContract_contractIsExist_iaDeleted() {
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "id=1");
        Assert.assertThat(1, Is.is(expected));
        contractDao.removeContract(1);
        sessionFactory.getCurrentSession().flush();
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "id=1");
        Assert.assertThat(0, Is.is(expected));
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "accounts", "contractId=1");
        Assert.assertThat(0, Is.is(expected));
        expected = JdbcTestUtils.countRowsInTable(jdbcTemplate, "clients");
        Assert.assertThat(3, Is.is(expected));
        expected = JdbcTestUtils.countRowsInTable(jdbcTemplate, "companies");
        Assert.assertThat(3, Is.is(expected));
    }

    @Test
    public void getContractList_contractsAreExist_isReceived() {
        List<Contract> contracts = contractDao.getContractList();
        assertEquals(4, contracts.size());
    }

    @Test
    public void getContractList_ContractsNotExist_notReceived() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "contracts");
        assertEquals(0, contractDao.getContractList().size());
    }

    @Test
    public void getContractListByClientId_contractIsExist_isReceived() {
        List<Contract> contracts = contractDao.getContractListByClientId(1);
        assertNotNull(contracts);
        assertEquals(2, contracts.size());
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "clientId=1");
        Assert.assertThat(2, Is.is(expected));
//        assertTrue(contracts.stream().
//                filter(c -> 1 == c.getClientId())
//                .findFirst()
//                .isPresent());
    }

    @Test
    public void getContractListByCompanyId_companyIsExist_isReceived() {
        List<Contract> contracts = contractDao.getContractListByCompanyId(1);
        assertNotNull(contracts);
        assertEquals(1, contracts.size());
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "companyId=1");
        Assert.assertThat(1, Is.is(expected));
    }

    @Test
    public void getCompanyListByClientId_contractsWirhClientAreExist_isReceived() {
        List<Company> companies = contractDao.getCompanyListByClientId(1);
        assertNotNull(companies);
        assertEquals(2, companies.size());
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "clientId=1");
        Assert.assertThat(2, Is.is(expected));
    }

    @Test
    public void getClientListByCompanyId_contractsWirhCompanyAreExist_isReceived() {
        List<Client> clients = contractDao.getClientListByCompanyId(1);
        assertNotNull(clients);
        assertEquals(1, clients.size());
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "companyId=1");
        Assert.assertThat(1, Is.is(expected));
    }
}