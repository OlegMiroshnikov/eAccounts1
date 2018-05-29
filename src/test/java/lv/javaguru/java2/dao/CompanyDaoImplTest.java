package lv.javaguru.java2.dao;

import static org.junit.Assert.*;

import lv.javaguru.java2.configs.SpringAppConfig;
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

import java.util.Optional;

@SqlGroup({
        @Sql("/mysql-scripts/create-database,sql"),
        @Sql("/mysql-scripts/clients-data.sql"),
        @Sql("/mysql-scripts/companies-data.sql"),
        @Sql("/mysql-scripts/contracts-data.sql"),
        @Sql("/mysql-scripts/accounts-data.sql"),
})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringAppConfig.class })
@Transactional
@Commit
public class CompanyDaoImplTest {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    private Company company;

    @Before
    public void setUp() {
        company = new Company("regNr", "name", "address", "eMail", "pathFromAccounts", "pathToAccounts");
    }

    @Test
    public void addCompany_companyNotNull_isAdded() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "companies");
        assertNull(company.getId());
        companyDao.addCompany(company);
        assertNotNull(company.getId());
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "companies", "regNr = 'regNr'");
        Assert.assertThat(1, Is.is(expected));
    }

    @Test
    public void updateCompany_companyNotNull_isUpdated() {
        company.setId(1);
        companyDao.updateCompany(company);
        sessionFactory.getCurrentSession().flush();
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "companies", "regNr = 'regNr' and id = 1");
        Assert.assertThat(1, Is.is(expected));
    }

    @Test
    public void getCompanyById_CompanyWithIdIsExist_isFound() {
        Optional<Company> company= companyDao.getCompanyById(1);
        assertNotNull(company);
        assertNotNull(company.get().getId());
        assertEquals((Integer) 1, company.get().getId());
        assertEquals("40003006912", company.get().getRegNr());
        assertEquals(1, company.get().getContracts().size());
    }

    @Test
    public void getCompanyById_companyWithIdNotExist_notFound() {
        Optional<Company> company= companyDao.getCompanyById(777);
        assertEquals(Optional.empty(), company);
    }

    @Test
    public void getCompanyByRegNr_companyWithRegNrisExist_isFound() {
        Optional<Company> company= companyDao.getCompanyByRegNr("40003006912");
        assertNotNull(company);
        assertNotNull(company.get().getRegNr());
        assertEquals("40003006912", company.get().getRegNr());
    }

    @Test
    public void getCompanyByRegNr_companyWithRegNrNotExist_notFound() {
        Optional<Company> company= companyDao.getCompanyByRegNr("wrongRegNr");
        assertEquals(Optional.empty(), company);
    }

    @Test
    public void getCompanyByRegNr_regNrIsNull_notFound() {
        Optional<Company> company = companyDao.getCompanyByRegNr(null);
        assertEquals(Optional.empty(), company);
    }

    @Test
    public void removeCompany_companyIsExist_iaDeleted() {
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "companies", "id=1");
        Assert.assertThat(1, Is.is(expected));
        companyDao.removeCompany(1);
        sessionFactory.getCurrentSession().flush();
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "companies", "id=1");
        Assert.assertThat(0, Is.is(expected));
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "companyId=1");
        Assert.assertThat(0, Is.is(expected));
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "accounts", "contractId=1");
        Assert.assertThat(0, Is.is(expected));
    }

    @Test
    public void getCompanyList_companiesAreExist_isReceived() {
        assertNotNull(companyDao.getCompanyList());
        assertEquals(3, companyDao.getCompanyList().size());
        assertTrue(companyDao.getCompanyList().stream().
                filter(c -> "40003006912".equals(c.getRegNr())).
                findFirst().
                isPresent());
    }

    @Test
    public void getCompanyList_companiesNotExist_notReceived() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "companies");
        assertEquals(0, companyDao.getCompanyList().size());
    }



}