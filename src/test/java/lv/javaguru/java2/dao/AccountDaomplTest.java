package lv.javaguru.java2.dao;

import static org.junit.Assert.*;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.domain.Account;
import lv.javaguru.java2.domain.Company;
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
public class AccountDaomplTest {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    private Account account;

    @Before
    public void setUp() {
        account = new Account(1, "testAccount");
    }

    @Test
    public void addAccount_accountNotNull_isAdded() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "accounts");
        assertNull(account.getId());
        accountDao.addAccount(account);
        assertNotNull(account.getId());
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "accounts", "fileName = 'testAccount'");
        Assert.assertThat(1, Is.is(expected));
    }

    @Test
    public void updateAccount_acountNotNull_isUpdated() {
        account.setId(1L);
        accountDao.updateAccount(account);
        sessionFactory.getCurrentSession().flush();
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "accounts", "fileName = 'testAccount' and id = 1");
        Assert.assertThat(1, Is.is(expected));
    }

    @Test
    public void getAccountById_accountWithIdIsExist_isFound() {
        Optional<Account> account = accountDao.getAccountById(1L);
        assertNotNull(account);
        assertNotNull(account.get().getId());
        assertEquals((Long) 1L, account.get().getId());
        assertEquals("RF1", account.get().getFileName());
        assertEquals("221/sft", account.get().getContract().getNumber());
    }

    @Test
    public void getAccountById_accountWithIdNotExist_notFound() {
        Optional<Account> account = accountDao.getAccountById(777L);
        assertEquals(Optional.empty(), account);
    }

    @Test
    public void getAccountById_accountWithNullDateFields_isReceived() {
        Optional<Account> account = accountDao.getAccountById(3L);
        assertNotNull(account);
        assertNull(account.get().getDateSending());
    }

    @Test
    public void getCompanyByContractId_companyWithIdIsExist_isFound() {
        Optional<Company> company = accountDao.getCompanyByContractId(1);
        assertNotNull(company);
        assertNotNull(company.get().getId());
        assertEquals((Integer) 1, company.get().getId());
        assertEquals("40003006912", company.get().getRegNr());
    }

    @Test
    public void getCompanyByContractId_companyWithIdnotExist_notFound() {
        Optional<Company> company = accountDao.getCompanyByContractId(777);
        assertEquals(Optional.empty(), company);
    }

    @Test
    public void getCompanyByContractId_companyWithNullDateFields_isReceived() {
        Optional<Company> company = accountDao.getCompanyByContractId(3);
        assertNotNull(company);
        assertNotNull(company.get().getId());
        assertEquals((Integer) 3, company.get().getId());
        assertNull(company.get().getAddress());
    }

    @Test
    public void removeAccount_accountIsExist_iaDeleted() {
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "accounts", "id=1");
        Assert.assertThat(1, Is.is(expected));
        accountDao.removeAccount(1L);
        sessionFactory.getCurrentSession().flush();
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "accounts", "id=1");
        Assert.assertThat(0, Is.is(expected));
    }

    @Test
    public void getAccountList_AccountsAreExist_isReceived() {
        List<Account> accounts = accountDao.getAccountList();
        assertEquals(3, accounts.size());
        assertTrue(accounts.stream()
                .filter(a -> "RF1".equals(a.getFileName()))
                .findFirst()
                .isPresent());
    }

    @Test
    public void getAccountList_accountsNotExist_notReceived() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "accounts");
        assertEquals(0, accountDao.getAccountList().size());
    }

    @Test
    public void getAccountListByContractId_contractIsExist_isReceived() {
        List<Account> accounts = accountDao.getAccountListByContractId(1);
        assertNotNull(accounts);
        assertEquals(1, accounts.size());
        assertTrue(accounts.stream().
                filter(a -> 1 == a.getContractId())
                .findFirst()
                .isPresent());
    }

}