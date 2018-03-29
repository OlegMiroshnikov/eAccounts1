package lv.javaguru.java2.dao;

import static org.junit.Assert.*;
import lv.javaguru.java2.domens.Contract;
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

import java.time.LocalDate;
import java.util.Optional;

public class ContractDaoJDBCImplTest extends JDBCDatasource{

    private ContractDaoInterface contractDaoImpl;
    private MysqlDataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private Contract contract;

    @Before
    public void setUp() {
        contractDaoImpl = new ContractDaoJDBCImpl();
        ResourceDatabasePopulator tables = new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("/mysql-scripts/create-database,sql"));
        tables.addScript(new ClassPathResource("/mysql-scripts/clients-data.sql"));
        tables.addScript(new ClassPathResource("/mysql-scripts/companies-data.sql"));
        tables.addScript(new ClassPathResource("/mysql-scripts/contracts-data.sql"));
        dataSource = new JDBCDatasource().getDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        DatabasePopulatorUtils.execute(tables, dataSource);
        contract = new Contract(1, 1, "number", LocalDate.now(), LocalDate.now(), LocalDate.now(), 10, 10, 0);
    }

    @Test
    public void addContract_contractNotNull_isAdded() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "contracts");
        assertNull(contract.getId());
        contractDaoImpl.addContract(contract);
        assertNotNull(contract.getId());
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "number = 'number'");
        Assert.assertThat(1, Is.is(expected));
    }

    @Test(expected = RuntimeException.class)
    public void addContract_contractIsNull_isException() {
        contractDaoImpl.addContract(new Contract());
    }

    @Test(expected = RuntimeException.class)
    public void addContract_contractWithCompanyAndClientYetExist_isException() {
        contractDaoImpl.addContract(new Contract(1, 1, "number", LocalDate.now(), LocalDate.now()));
    }

    @Test(expected = RuntimeException.class)
    public void addContract_companyNotExist_isException() {
        contractDaoImpl.addContract(new Contract(777, 1, "number", LocalDate.now(), LocalDate.now()));
    }

    @Test(expected = RuntimeException.class)
    public void addContract_clientNotExist_isException() {
        contractDaoImpl.addContract(new Contract(1, 777, "number", LocalDate.now(), LocalDate.now()));
    }

    @Test
    public void getContractById_contractWithIdIsExist_isFound() {
        Optional<Contract> contract= contractDaoImpl.getContractById(1);
        assertNotNull(contract);
        assertNotNull(contract.get().getId());
        assertEquals((Integer) 1, contract.get().getId());
        assertEquals("221/sft", contract.get().getNumber());
    }

    @Test
    public void getContractById_contractWithIdNotExist_notFound() {
        Optional<Contract> contract= contractDaoImpl.getContractById(777);
        assertEquals(Optional.empty(), contract);
    }

    @Test
    public void getContractById_contractWithNullDateFields_isReceived() {
        Optional<Contract> contract= contractDaoImpl.getContractById(3);
        assertNotNull(contract);
        assertNotNull(contract.get().getId());
        assertEquals((Integer) 3, contract.get().getId());
        assertNull(contract.get().getDateEnd());
    }

    @Test(expected = RuntimeException.class)
    public void getContractById_idIsNull_Exception() {
        Optional<Contract> contract = contractDaoImpl.getContractById(null);
    }

    @Test
    public void getContractByCompanyIdAndClientId_contractIsExist_isFound() {
        Optional<Contract> contract= contractDaoImpl.getContractByCompanyIdAndClientId(1, 1);
        assertNotNull(contract);
        assertNotNull(contract.get().getId());
        assertEquals((Integer) 1, contract.get().getId());
        assertEquals("221/sft", contract.get().getNumber());
    }

    @Test
    public void getContractByCompanyIdAndClientId_contractNotExist_notFound() {
        Optional<Contract> contract= contractDaoImpl.getContractByCompanyIdAndClientId(777, 777);
        assertEquals(Optional.empty(), contract);
    }

    @Test
    public void getContractByCompanyIdAndClientId_contractWithNullDateFields_isReceived() {
        Optional<Contract> contract= contractDaoImpl.getContractByCompanyIdAndClientId(3, 3);
        assertNotNull(contract);
        assertNotNull(contract.get().getId());
        assertEquals((Integer) 3, contract.get().getId());
        assertNull(contract.get().getDateEnd());
    }

    @Test
    public void removeContract_contractIsExist_iaDeleted() {
        contract.setId(1);
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "id=1");
        Assert.assertThat(1, Is.is(expected));
        contractDaoImpl.removeContract(contract);
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "id=1");
        Assert.assertThat(0, Is.is(expected));
    }

    @Test
    public void removeContract_contractNotExist_notDeleted() {
        contract.setId(777);
        int expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "id=777");
        Assert.assertThat(0, Is.is(expected));
        contractDaoImpl.removeContract(contract);
        expected = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "contracts", "id=777");
        Assert.assertThat(0, Is.is(expected));
    }

    @Test(expected = RuntimeException.class)
    public void removeContract_contractIsNull_Exception() {
        contractDaoImpl.removeContract(null);
    }

    @Test
    public void getContractList_contractsAreExist_isReceived() {
        assertNotNull(contractDaoImpl.getContractList());
        assertEquals(3,contractDaoImpl.getContractList().size());
        assertTrue(contractDaoImpl.getContractList().stream().
                filter(c -> "221/sft".equals(c.getNumber())).
                findFirst().
                isPresent());
    }

    @Test
    public void getContractList_ContractsNotExist_notReceived() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "contracts");
        assertEquals(0, contractDaoImpl.getContractList().size());
    }


}