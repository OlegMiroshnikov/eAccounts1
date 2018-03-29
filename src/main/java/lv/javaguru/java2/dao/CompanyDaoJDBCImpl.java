package lv.javaguru.java2.dao;

import lv.javaguru.java2.domens.Company;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CompanyDaoJDBCImpl extends JDBCDatasource implements CompanyDaoInterface {
    @Override
    public void addCompany(Company company) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into COMPANIES (regNr, name, address, eMail, pathFromAccounts, pathToAccounts) " +
                    "values(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, company.getRegNr());
            preparedStatement.setString(2, company.getName());
            preparedStatement.setString(3, company.getAddress());
            preparedStatement.setString(4, company.getEMail());
            preparedStatement.setString(5, company.getPathFromAccounts());
            preparedStatement.setString(6, company.getPathToAccounts());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                company.setId(rs.getInt(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute CompanyDaoJDBCDatabaseImpl.addCompany()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            System.out.println(company);
            closeConnection(connection);
        }
    }

    @Override
    public Optional<Company> getCompanyById(Integer id) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from COMPANIES where id = ?";
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
            System.out.println("Exception while execute CompanyDaoJDBCDatabaseImpl.getCompanyById()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<Company> getCompanyByRegNr(String regNr) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from COMPANIES where regNr = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, regNr);
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
            System.out.println("Exception while execute CompanyDaoJDBCDatabaseImpl.getCompanyByRegNr()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void updateCompany(Company company) {
    }

    @Override
    public void removeCompany(Company company) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "delete from COMPANIES where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, company.getId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute CompanyDaoJDBCDatabaseImpl.removeCompany()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Company> getCompanyList() {
        List<Company> companies = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from COMPANIES";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Company company = new Company();
                company.setId(resultSet.getInt("id"));
                company.setRegNr(resultSet.getString("regNr"));
                company.setName(resultSet.getString("name"));
                company.setAddress(resultSet.getString("address"));
                company.setEMail(resultSet.getString("eMail"));
                company.setPathFromAccounts(resultSet.getString("pathFromAccounts"));
                company.setPathToAccounts(resultSet.getString("pathToAccounts"));
                companies.add(company);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting company list CompanyDaoJDBCDatabaseImpl.getCompanyList()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return companies;
    }

    @Override
    public List<Company> getCompanyListByClientId(Integer clientId) {
        return null;
    }
}
