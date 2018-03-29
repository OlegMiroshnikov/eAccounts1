package lv.javaguru.java2.dao;

import lv.javaguru.java2.domens.Contract;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ContractDaoJDBCImpl extends JDBCDatasource implements ContractDaoInterface {

    @Override
    public void addContract(Contract contract) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into CONTRACTS (companyId, clientId, number, dateSign," +
                    "dateBegin, dateEnd, dayToSendAccount, countDaysToSendReminder, Status) " +
                    "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, contract.getCompanyId());
            preparedStatement.setInt(2, contract.getClientId());
            preparedStatement.setString(3, contract.getNumber());
            preparedStatement.setDate(4, Date.valueOf(contract.getDateSign()));
            preparedStatement.setDate(5, Date.valueOf(contract.getDateBegin()));
            if (contract.getDateEnd() != null) {
                preparedStatement.setDate(6, Date.valueOf(contract.getDateEnd()));
            } else {
                preparedStatement.setDate(6, null);
            }
            if (contract.getDayToSendAccount() != null){
                preparedStatement.setInt(7, contract.getDayToSendAccount());
            } else {
                preparedStatement.setInt(7, 10);
            }
            if (contract.getCountDaysToSendReminder() != null){
                preparedStatement.setInt(8, contract.getCountDaysToSendReminder());    preparedStatement.setInt(7, contract.getDayToSendAccount());
            } else{
                preparedStatement.setInt(8, 10);
            }
            if (contract.getStatus() != null){
                preparedStatement.setInt(9, contract.getStatus());
            }  else{
                preparedStatement.setInt(9, 0);
            }
//            preparedStatement.setInt(7, contract.getDayToSendAccount());
//            preparedStatement.setInt(8, contract.getCountDaysToSendReminder());
//            preparedStatement.setInt(9, contract.getStatus());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                contract.setId(rs.getInt(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute ContractDaoJDBCDatabaseImpl.addContract()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            System.out.println(contract);
            closeConnection(connection);
        }

    }

    @Override
    public Optional<Contract> getContractById(Integer id) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from CONTRACTS where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Contract contract = null;
            if (resultSet.next()) {
                contract = new Contract();
                contract.setId(resultSet.getInt("id"));
                contract.setCompanyId(resultSet.getInt("companyId"));
                contract.setClientId(resultSet.getInt("clientId"));
                contract.setNumber(resultSet.getString("number"));
                contract.setDateSign(resultSet.getDate("dateSign").toLocalDate());
                contract.setDateBegin(resultSet.getDate("dateBegin").toLocalDate());
                Date dateEnd = resultSet.getDate("dateEnd");
                if (!resultSet.wasNull()) {
                    contract.setDateEnd(dateEnd.toLocalDate());
                }
                contract.setDayToSendAccount(resultSet.getInt("dayToSendAccount"));
                contract.setCountDaysToSendReminder(resultSet.getInt("countDaysToSendReminder"));
                contract.setStatus(resultSet.getInt("status"));
            }
            return Optional.ofNullable(contract);
        } catch (Throwable e) {
            System.out.println("Exception while execute ContractDaoJDBCDatabaseImpl.getContractById()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<Contract> getContractByCompanyIdAndClientId(Integer companyId, Integer clientId) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from CONTRACTS where companyId = ? and clientId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, companyId);
            preparedStatement.setInt(2, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Contract contract = null;
            if (resultSet.next()) {
                contract = new Contract();
                contract.setId(resultSet.getInt("id"));
                contract.setCompanyId(resultSet.getInt("companyId"));
                contract.setClientId(resultSet.getInt("clientId"));
                contract.setNumber(resultSet.getString("number"));
                contract.setDateSign(resultSet.getDate("dateSign").toLocalDate());
                contract.setDateBegin(resultSet.getDate("dateBegin").toLocalDate());
                Date dateEnd = resultSet.getDate("dateEnd");
                if (!resultSet.wasNull()) {
                    contract.setDateEnd(dateEnd.toLocalDate());
                }
                contract.setDayToSendAccount(resultSet.getInt("dayToSendAccount"));
                contract.setCountDaysToSendReminder(resultSet.getInt("countDaysToSendReminder"));
                contract.setStatus(resultSet.getInt("status"));
            }
            return Optional.ofNullable(contract);
        } catch (Throwable e) {
            System.out.println("Exception while execute ContractDaoJDBCDatabaseImpl.getContractByCompanyIdAndClientId()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public void updateContract(Contract contract) {
    }


    @Override
    public void removeContract(Contract contract) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "delete from CONTRACTS where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, contract.getId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute ContractDaoJDBCDatabaseImpl.removeContract()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Contract> getContractList() {
        List<Contract> contracts = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from CONTRACTS";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Contract contract = new Contract();
                contract.setId(resultSet.getInt("id"));
                contract.setCompanyId(resultSet.getInt("companyId"));
                contract.setClientId(resultSet.getInt("clientId"));
                contract.setNumber(resultSet.getString("number"));
                contract.setDateSign(resultSet.getDate("dateSign").toLocalDate());
                contract.setDateBegin(resultSet.getDate("dateBegin").toLocalDate());
                Date dateEnd = resultSet.getDate("dateEnd");
                if (!resultSet.wasNull()) {
                    contract.setDateEnd(dateEnd.toLocalDate());
                }
                contract.setDayToSendAccount(resultSet.getInt("dayToSendAccount"));
                contract.setCountDaysToSendReminder(resultSet.getInt("countDaysToSendReminder"));
                contract.setStatus(resultSet.getInt("status"));
                contracts.add(contract);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting contract list ContractDaoJDBCDatabaseImpl.getContractList()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return contracts;
    }

    @Override
    public List<Contract> getContractListByClientId(Integer clientID) {
        return null;
    }

}
