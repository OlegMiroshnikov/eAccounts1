package lv.javaguru.java2.dao;

import lv.javaguru.java2.domens.Account;
import lv.javaguru.java2.domens.Company;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Date;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Component
public class AccountDaoJDBCImpl extends JDBCDatasource implements AccountDaoInterface {

    @Override
    public void addAccount(Account account) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into ACCOUNTS(contractId, fileName) values(?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, account.getContractId());
            preparedStatement.setString(2, account.getFileName());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                account.setId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute AccountDaoJDBCDatabaseImpl.addAccount()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            System.out.println(account);
            closeConnection(connection);
        }
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from ACCOUNTS where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Account account = null;
            if (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setContractId(resultSet.getInt("contractId"));
                account.setFileName(resultSet.getString("fileName"));
                Date dateSending = resultSet.getDate("dateSending");
                if (!resultSet.wasNull()) {
                    account.setDateSending(dateSending.toLocalDate());
                }
                Date dateViewing = resultSet.getDate("dateViewing");
                if (!resultSet.wasNull()) {
                    account.setDateViewing(dateViewing.toLocalDate());
                }
                Date dateReminderSending = resultSet.getDate("dateReminderSending");
                if (!resultSet.wasNull()) {
                    account.setDateReminderSending(dateReminderSending.toLocalDate());
                }
            }
            return Optional.ofNullable(account);
        } catch (Throwable e) {
            System.out.println("Exception while execute AccountDaoJDBCDatabaseImpl.getAccountById()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<Company> getCompanyByContractId(Integer id) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from COMPANIES WHERE id = " +
                    "(select companyId from CONTRACTS WHERE id = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Company company = null;
            if (resultSet.next()) {
                company = new Company();
                company.setId(resultSet.getInt("id"));
                company.setRegNr(resultSet.getString("regNr"));
                company.setName(resultSet.getString("name"));
                company.setAddress(resultSet.getString("address"));
                company.setEMail(resultSet.getString("eMail"));
                company.setPathFromAccounts(resultSet.getString("pathFromAccounts"));
                company.setPathToAccounts(resultSet.getString("pathToAccounts"));
            }
            return Optional.ofNullable(company);
        } catch (Throwable e) {
            System.out.println("Exception while execute AccountDaoJDBCDatabaseImpl.getCompanyByContractId()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void updateAccount(Account account) {
    }

    @Override
    public void removeAccount(Account account) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "delete from ACCOUNTS where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, account.getId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute AccountDaoJDBCDatabaseImpl.removeAccount()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Account> getAccountList() {
        List<Account> accounts = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from ACCOUNTS";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setContractId(resultSet.getInt("contractId"));
                account.setFileName(resultSet.getString("fileName"));
                Date dateSending = resultSet.getDate("dateSending");
                if (!resultSet.wasNull()) {
                    account.setDateSending(dateSending.toLocalDate());
                }
                Date dateViewing = resultSet.getDate("dateViewing");
                if (!resultSet.wasNull()) {
                    account.setDateViewing(dateViewing.toLocalDate());
                }
                Date dateReminderSending = resultSet.getDate("dateReminderSending");
                if (!resultSet.wasNull()) {
                    account.setDateReminderSending(dateReminderSending.toLocalDate());
                }
                accounts.add(account);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting account list AccountDaoJDBCDatabaseImpl.getAccountList()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return accounts;
    }

    @Override
    public List<Account> getAccountListByContractId(Integer contractId) {
        return null;
    }
}
