package lv.javaguru.java2.dao;

import static org.junit.Assert.*;
import lv.javaguru.java2.domens.Company;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.jdbc.JdbcTestUtils;
import java.util.Optional;

public class CompanyDaoJDBCImplTest extends JDBCDatasource{
    private CompanyDaoInterface companyDaoImpl;
    private MysqlDataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private Company company;

    @Before
    public void setUp() {
        companyDaoImpl = new CompanyDaoJDBCImpl();
        ResourceDatabasePopulator tables = new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("/mysql-scripts/create-database,sql"));
        tables.addScript(new ClassPathResource("/mysql-scripts/companies-data.sql"));
        dataSource = new JDBCDatasource().getDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        DatabasePopulatorUtils.execute(tables, dataSource);
        company = new Company("regNr", "name", "address", "eMail", "pathFromAccounts", "pathToAccounts");
    }

    @Test
    public void addCompany_companyNotNull_isAdded() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "companies");
        assertNull(company.getId());
        companyDaoImpl.addCompany(company);
        assertNotNull(company.getId());
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "companies", "regNr = 'regNr'");
        Assert.assertThat(1, Is.is(expected));
    }

    @Test(expected = RuntimeException.class)
    public void addCompany_companyIsNull_Exception() {
        companyDaoImpl.addCompany(new Company());
    }

    @Test(expected = RuntimeException.class)
    public void addCompany_companyWithDublicatedRegNr_isException() {
        companyDaoImpl.addCompany(new Company("40003006912", "name", "address", "eMail", "pathFromAccounts", "pathToAccounnts"));
    }

    @Test
    public void getCompanyById_CompanyWithIdIsExist_isFound() {
        Optional<Company> company= companyDaoImpl.getCompanyById(1);
        assertNotNull(company);
        assertNotNull(company.get().getId());
        assertEquals((Integer) 1, company.get().getId());
        assertEquals("40003006912", company.get().getRegNr());
    }

    @Test
    public void getCompanyById_companyWithIdNotExist_notFound() {
        Optional<Company> company= companyDaoImpl.getCompanyById(777);
        assertEquals(Optional.empty(), company);
    }

    @Test(expected = RuntimeException.class)
    public void getCompanyById_idIsNull_Exception() {
        Optional<Company> company = companyDaoImpl.getCompanyById(null);
    }

    @Test
    public void getCompanyByRegNr_companyWithRegNrisExist_isFound() {
        Optional<Company> company= companyDaoImpl.getCompanyByRegNr("40003006912");
        assertNotNull(company);
        assertNotNull(company.get().getRegNr());
        assertEquals("40003006912", company.get().getRegNr());
    }

    @Test
    public void getCompanyByRegNr_companyWithRegNrNotExist_notFound() {
        Optional<Company> company= companyDaoImpl.getCompanyByRegNr("wrongRegNr");
        assertEquals(Optional.empty(), company);
    }

    @Test
    public void getCompanyByRegNr_regNrIsNull_notFound() {
        Optional<Company> company = companyDaoImpl.getCompanyByRegNr(null);
        assertEquals(Optional.empty(), company);
    }

    @Test
    public void removeCompany_companyIsExist_iaDeleted() {
        company.setId(1);
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "companies", "id=1");
        Assert.assertThat(1, Is.is(expected));
        companyDaoImpl.removeCompany(company);
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "companies", "id=1");
        Assert.assertThat(0, Is.is(expected));
    }

    @Test
    public void removeCompany_companyNotExist_notDeleted() {
        company.setId(777);
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "companies", "id=777");
        Assert.assertThat(0, Is.is(expected));
        companyDaoImpl.removeCompany(company);
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "companies", "id=777");
        Assert.assertThat(0, Is.is(expected));
    }

    @Test(expected = RuntimeException.class)
    public void removeCompany_companyIsNull_Exception() {
        companyDaoImpl.removeCompany(null);
    }

    @Test
    public void getCompanyList_companiesAreExist_isReceived() {
        assertNotNull(companyDaoImpl.getCompanyList());
        assertEquals(3,companyDaoImpl.getCompanyList().size());
        assertTrue(companyDaoImpl.getCompanyList().stream().
                filter(c -> "40003006912".equals(c.getRegNr())).
                findFirst().
                isPresent());
    }

    @Test
    public void getCompanyList_companiesNotExist_notReceived() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "companies");
        assertEquals(0, companyDaoImpl.getCompanyList().size());
    }



}