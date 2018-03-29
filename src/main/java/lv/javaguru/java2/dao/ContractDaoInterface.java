package lv.javaguru.java2.dao;

import lv.javaguru.java2.domens.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractDaoInterface {
    void addContract(Contract contract);

    Optional<Contract> getContractById(Integer id);

    Optional<Contract> getContractByCompanyIdAndClientId(Integer CompanyId, Integer ClientId);

    void updateContract(Contract contract);

    void removeContract(Contract contract);

    List<Contract> getContractList();

    List<Contract> getContractListByClientId(Integer clientId);
}
