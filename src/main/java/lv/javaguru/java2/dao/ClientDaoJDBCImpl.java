package lv.javaguru.java2.dao;

import lv.javaguru.java2.domens.Client;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ClientDaoJDBCImpl extends JDBCDatasource implements ClientDaoInterface {

    @Override
    public void addClient(Client client) {
        Connection connection = null;
        try {
            connection = getConnection();
//            String sql = "insert into CLIENTS(personalCode, name, address, eMail, addUser) values(?, ?, ?, ?, SUBSTRING_INDEX(USER(),'@',1))";
            String sql = "insert into CLIENTS(personalCode, name, address, eMail) values(?, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getPersonalCode());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getAddress());
            preparedStatement.setString(4, client.getEMail());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                client.setId(rs.getInt(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute ClientDaoJDBCDatabaseImpl.addClient()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            System.out.println(client);
            closeConnection(connection);
        }
    }

    @Override
    public Optional<Client> getClientById(Integer id) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from CLIENTS where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Client client = null;
            if (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setPersonalCode(resultSet.getString("personalCode"));
                client.setName(resultSet.getString("name"));
                client.setAddress(resultSet.getString("address"));
                client.setEMail(resultSet.getString("eMail"));
            }
            return Optional.ofNullable(client);
        } catch (Throwable e) {
            System.out.println("Exception while execute ClientDaoJDBCDatabaseImpl.getClientById()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<Client> getClientByPersonalCode(String personalCode) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from CLIENTS where personalCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, personalCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            Client client = null;
            if (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setPersonalCode(resultSet.getString("personalCode"));
                client.setName(resultSet.getString("name"));
                client.setAddress(resultSet.getString("address"));
                client.setEMail(resultSet.getString("eMail"));
            }
            return Optional.ofNullable(client);
        } catch (Throwable e) {
            System.out.println("Exception while execute ClientDaoJDBCDatabaseImpl.getClientByPersonalCode()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void removeClient(Client client) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "delete from CLIENTS where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, client.getId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute ClientDaoJDBCDatabaseImpl.removeClient()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Client> getClientList() {
        List<Client> clients = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from CLIENTS";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setPersonalCode(resultSet.getString("personalCode"));
                client.setName(resultSet.getString("name"));
                client.setAddress(resultSet.getString("address"));
                client.setEMail(resultSet.getString("eMail"));
                clients.add(client);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting client list ClientDaoJDBCDatabaseImpl.getClientList()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return clients;
    }

    @Override
    public void updateClient(Client client) {
    }
}