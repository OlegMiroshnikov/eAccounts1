package lv.javaguru.java2.dao;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.configs.SpringWebMvcInitializer;
import lv.javaguru.java2.configs.WebMVCConfig;
import lv.javaguru.java2.domain.Client;
import org.hamcrest.core.Is;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
//import javax.transaction.Transactional;
import java.util.Optional;


@SqlGroup({
        @Sql("/mysql-scripts/create-database,sql"),
        @Sql("/mysql-scripts/clients-data.sql"),
        @Sql("/mysql-scripts/companies-data.sql"),
        @Sql("/mysql-scripts/contracts-data.sql"),
        @Sql("/mysql-scripts/accounts-data.sql"),
})
//@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
@Transactional
@Commit
public class ClientDaoImplTest {

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SessionFactory sessionFactory;


    private Client client;

    @Before
    public void setUp() {
        client = new Client("personalCode", "name", "address", "eMail");
    }


    @Test
    public void addClient_clientNotNull_isAdded() {
//        JdbcTestUtils.deleteFromTables(jdbcTemplate, "accounts");
//        JdbcTestUtils.deleteFromTables(jdbcTemplate, "contracts");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "clients");
        assertNull(client.getId());
        clientDao.addClient(client);
        assertNotNull(client.getId());
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "clients", "personalCode = 'personalCode'");
        Assert.assertThat(1, Is.is(expected));
    }

    @Test
    public void updateClient_clientNotNull_isUpdated() {
        client.setId(1);
        clientDao.updateClient(client);
        sessionFactory.getCurrentSession().flush();
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "clients", "personalCode = 'personalCode' and id = 1");
        Assert.assertThat(1, Is.is(expected));
    }

    @Test(expected = ConstraintViolationException.class)
    @Ignore
    public void addClient_clientWithDublicatedPersonalCode_notAdded() throws ConstraintViolationException {
        clientDao.addClient(new Client("100691-13115", "name", "address", "eMail"));
    }

    @Test
    public void getClientById_clientWithIdIsExist_isFound() {
        Optional<Client> client = clientDao.getClientById(1);
        assertNotNull(client);
        assertNotNull(client.get().getId());
        assertEquals((Integer) 1, client.get().getId());
        assertEquals("100691-13115", client.get().getPersonalCode());
        assertEquals(2, client.get().getContracts().size());
    }

    @Test
    public void getClientById_clientWithIdNotExist_notFound() {
        Optional<Client> client = clientDao.getClientById(777);
        assertEquals(Optional.empty(), client);
    }

    @Test
    public void getClientById_idIsNull_notFound() {
        Optional<Client> client = clientDao.getClientById(null);
        assertEquals(Optional.empty(), client);
    }

    @Test
    public void getClientByPersonalCode_clientWithPersonalCodeIsExist_isFound() {
        Optional<Client> client = clientDao.getClientByPersonalCode("100691-13115");
        assertNotNull(client);
        assertNotNull(client.get().getPersonalCode());
        assertEquals("100691-13115", client.get().getPersonalCode());
    }

    @Test
    public void getClientByPersonalCode_clientWithPersonalCodeNotExist_notFound() {
        Optional<Client> client = clientDao.getClientByPersonalCode("wrongPersonalCode");
        assertEquals(Optional.empty(), client);
    }

    @Test
    public void getClientByPersonalCode_personalCodeIsNull_notFound() {
        Optional<Client> client = clientDao.getClientByPersonalCode(null);
        assertEquals(Optional.empty(), client);
    }

    @Test
    public void removeClient_clientIsExist_iaDeleted() {
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "clients", "id=1");
        Assert.assertThat(1, Is.is(expected));
        clientDao.removeClient(1);
        sessionFactory.getCurrentSession().flush();
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "clients", "id=1");
        Assert.assertThat(0, Is.is(expected));
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "clientId=1");
        Assert.assertThat(0, Is.is(expected));
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "accounts", "contractId=1");
        Assert.assertThat(0, Is.is(expected));
    }

    @Test
    public void getClientList_clientsAreExist_isReceived() {
        assertNotNull(clientDao.getClientList());
        assertEquals(3, clientDao.getClientList().size());
        assertTrue(clientDao.getClientList().stream()
                .filter(c -> "100691-13115".equals(c.getPersonalCode()))
                .findFirst()
                .isPresent());
    }

    @Test
    public void getClientList_clientsNotExist_notReceived() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "clients");
        assertEquals(0, clientDao.getClientList().size());
    }

}