package lv.javaguru.java2.dao;

import static org.junit.Assert.*;

import lv.javaguru.java2.domens.Account;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import lv.javaguru.java2.domens.Company;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.time.LocalDate;
import java.util.Optional;

public class AccountDaoJDBCImplTest extends JDBCDatasource {
    private AccountDaoInterface accountDaoImpl;
    private MysqlDataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private Account account;

    @Before
    public void setUp() {
        accountDaoImpl = new AccountDaoJDBCImpl();
        ResourceDatabasePopulator tables = new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("/mysql-scripts/create-database,sql"));
        tables.addScript(new ClassPathResource("/mysql-scripts/clients-data.sql"));
        tables.addScript(new ClassPathResource("/mysql-scripts/companies-data.sql"));
        tables.addScript(new ClassPathResource("/mysql-scripts/contracts-data.sql"));
        tables.addScript(new ClassPathResource("/mysql-scripts/accounts-data.sql"));
        dataSource = new JDBCDatasource().getDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        DatabasePopulatorUtils.execute(tables, dataSource);
        account = new Account(1, "testAccount");
    }

    @Test
    public void addAccount_accountNotNull_isAdded() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "accounts");
        assertNull(account.getId());
        accountDaoImpl.addAccount(account);
        assertNotNull(account.getId());
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "accounts", "fileName = 'testAccount'");
        Assert.assertThat(1, Is.is(expected));
    }

    @Test(expected = RuntimeException.class)
    public void addAccount_accountIsNull_isException() {
        accountDaoImpl.addAccount(new Account());
    }

    @Test(expected = RuntimeException.class)
    public void addAccount_contractNotExist_isException() {
        accountDaoImpl.addAccount(new Account(777, "fileName"));
    }

    @Test
    public void getAccountById_accountWithIdIsExist_isFound() {
        Optional<Account> account = accountDaoImpl.getAccountById(1L);
        assertNotNull(account);
        assertNotNull(account.get().getId());
        assertEquals((Long)1L, account.get().getId());
        assertEquals("RF1", account.get().getFileName());
    }

    @Test
    public void getAccountById_accountWithIdNotExist_notFound() {
        Optional<Account> account = accountDaoImpl.getAccountById(777L);
        assertEquals(Optional.empty(), account);
    }

    @Test
    public void getAccountById_accountWithNullDateFields_isReceived() {
        Optional<Account> account = accountDaoImpl.getAccountById(3L);
        assertNotNull(account);
        assertNull(account.get().getDateSending());
    }

    @Test
    public void getCompanyByContractId_companyWithIdIsExist_isFound() {
        Optional<Company> company = accountDaoImpl.getCompanyByContractId(1);
        assertNotNull(company);
        assertNotNull(company.get().getId());
        assertEquals((Integer) 1, company.get().getId());
        assertEquals("40003006912", company.get().getRegNr());
    }

    @Test
    public void getCompanyByContractId_companyWithIdnotExist_notFound() {
        Optional<Company> company = accountDaoImpl.getCompanyByContractId(777);
        assertEquals(Optional.empty(), company);
    }

    @Test
    public void getCompanyByContractId_companyWithNullDateFields_isReceived() {
        Optional<Company> company = accountDaoImpl.getCompanyByContractId(3);
        assertNotNull(company);
        assertNotNull(company.get().getId());
        assertEquals((Integer) 3, company.get().getId());
        assertNull(company.get().getAddress());
    }

    @Test(expected = RuntimeException.class)
    public void getAccountById_idIsNull_Exception() {
        Optional<Account> account = accountDaoImpl.getAccountById(null);
    }

    @Test
    public void removeAccount_accountIsExist_iaDeleted() {
        account.setId(1L);
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "accounts", "id=1");
        Assert.assertThat(1, Is.is(expected));
        accountDaoImpl.removeAccount(account);
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "accounts", "id=1");
        Assert.assertThat(0, Is.is(expected));
    }

    @Test
    public void removeAccount_accountNotExist_notDeleted() {
        account.setId(777L);
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "accounts", "id=777");
        Assert.assertThat(0, Is.is(expected));
        accountDaoImpl.removeAccount(account);
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "accounts", "id=777");
        Assert.assertThat(0, Is.is(expected));
    }

    @Test(expected = RuntimeException.class)
    public void removeAccount_accountIsNull_Exception() {
        accountDaoImpl.removeAccount(null);
    }

    @Test
    public void getAccountList_AccountsAreExist_isReceived() {
        assertNotNull(accountDaoImpl.getAccountList());
        assertEquals(3, accountDaoImpl.getAccountList().size());
        assertTrue(accountDaoImpl.getAccountList().stream().
                filter(a -> "RF1".equals(a.getFileName())).
                findFirst().
                isPresent());
    }

    @Test
    public void getAccountList_accountsNotExist_notReceived() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "accounts");
        assertEquals(0, accountDaoImpl.getAccountList().size());
    }


}