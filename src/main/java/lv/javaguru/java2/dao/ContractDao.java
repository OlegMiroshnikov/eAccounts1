package lv.javaguru.java2.dao;

import lv.javaguru.java2.domain.Client;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractDao {
    void addContract(Contract contract);

    void updateContract(Contract contract);

    void removeContract(Integer id);

    Optional<Contract> getContractById(Integer id);

    Optional<Contract> getContractByCompanyIdAndClientId(Integer companyId, Integer clientId);

    List<Contract> getContractList();

    List<Contract> getContractListByClientId(Integer clientId);

    List<Contract> getContractListByCompanyId(Integer companyId);

    List<Company> getCompanyListByClientId(Integer clientId);

    List<Client> getClientListByCompanyId(Integer companyId);

}

