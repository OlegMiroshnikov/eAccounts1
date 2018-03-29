package lv.javaguru.java2.dao;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.domens.Client;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.hamcrest.core.Is;
import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//
//@SqlGroup({
//        @Sql("/mysql-scripts/create-database,sql"),
//        @Sql("/mysql-scripts/client-data.sql"),
//})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringAppConfig.class })
//@Transactional
public class ClientDaoJDBCImplTest extends JDBCDatasource {

    @Autowired
    private ClientDaoInterface clientDaoImpl;
    private MysqlDataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private Client client;

    @Before
    public void setUp() {
        ResourceDatabasePopulator tables = new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("/mysql-scripts/create-database,sql"));
        tables.addScript(new ClassPathResource("/mysql-scripts/clients-data.sql"));
        dataSource = new JDBCDatasource().getDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        DatabasePopulatorUtils.execute(tables, dataSource);
        client = new Client("personalCode", "name", "address", "eMail");
    }

    @Test
    public void addClient_clientNotNull_isAdded() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "clients");
        assertNull(client.getId());
        clientDaoImpl.addClient(client);
        assertNotNull(client.getId());
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "clients", "personalCode = 'personalCode'");
        Assert.assertThat(1, Is.is(expected));
    }

    @Test(expected = RuntimeException.class)
    public void addClient_clientIsNull_isException() {
        clientDaoImpl.addClient(new Client());
    }

    @Test(expected = RuntimeException.class)
    public void addClient_clientWithDublicatedPersonalCode_isException() {
        clientDaoImpl.addClient(new Client("100691-13115", "name", "address", "eMail"));
    }


    @Test
    public void getClientById_clientWithIdIsExist_isFound() {
        Optional<Client> client= clientDaoImpl.getClientById(1);
        assertNotNull(client);
        assertNotNull(client.get().getId());
        assertEquals((Integer) 1, client.get().getId());
        assertEquals("100691-13115", client.get().getPersonalCode());
    }

    @Test
    public void getClientById_clientWithIdNotExist_notFound() {
        Optional<Client> client= clientDaoImpl.getClientById(777);
        assertEquals(Optional.empty(), client);
    }

    @Test(expected = RuntimeException.class)
    public void getClientById_idIsNull_Exception() {
        Optional<Client> client = clientDaoImpl.getClientById(null);
    }

    @Test
    public void getClientByPersonalCode_clientWithPersonalCodeIsExist_isFound() {
        Optional<Client> client= clientDaoImpl.getClientByPersonalCode("100691-13115");
        assertNotNull(client);
        assertNotNull(client.get().getPersonalCode());
//        assertThat(client.get().getPersonalCode(), is("100691-13115"));
        assertEquals("100691-13115", client.get().getPersonalCode());
    }

    @Test
    public void getClientByPersonalCode_clientWithPersonalCodeNotExist_notFound() {
        Optional<Client> client= clientDaoImpl.getClientByPersonalCode("wrongPersonalCode");
        assertEquals(Optional.empty(), client);
    }

    @Test
    public void getClientByPersonalCode_personalCodeIsNull_notFound() {
        Optional<Client> client = clientDaoImpl.getClientByPersonalCode(null);
        assertEquals(Optional.empty(), client);
    }

    @Test
    public void removeClient_clientIsExist_iaDeleted() {
        client.setId(1);
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "clients", "id=1");
        Assert.assertThat(1, Is.is(expected));
        clientDaoImpl.removeClient(client);
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "clients", "id=1");
        Assert.assertThat(0, Is.is(expected));
    }

    @Test
    public void removeClient_clientNotExist_notDeleted() {
        client.setId(777);
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "clients", "id=777");
        Assert.assertThat(0, Is.is(expected));
        clientDaoImpl.removeClient(client);
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "clients", "id=777");
        Assert.assertThat(0, Is.is(expected));
    }

    @Test(expected = RuntimeException.class)
    public void removeClient_clientIsNull_Exception() {
        clientDaoImpl.removeClient(null);
    }

    @Test
    public void getClientList_clientsAreExist_isReceived() {
        assertNotNull(clientDaoImpl.getClientList());
        assertEquals(3,clientDaoImpl.getClientList().size());
        assertTrue(clientDaoImpl.getClientList().stream().
                filter(c -> "100691-13115".equals(c.getPersonalCode())).
                findFirst().
                isPresent());
    }

    @Test
    public void getClientList_clientsNotExist_notReceived() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "clients");
        assertEquals(0, clientDaoImpl.getClientList().size());
    }

}