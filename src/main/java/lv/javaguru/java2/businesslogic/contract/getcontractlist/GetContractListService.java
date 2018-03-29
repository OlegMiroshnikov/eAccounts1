package lv.javaguru.java2.businesslogic.contract.getcontractlist;

import lv.javaguru.java2.dao.ContractDaoInterface;
import lv.javaguru.java2.domens.Contract;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetContractListService {

    private ContractDaoInterface contractDaoImpl;

    public GetContractListService(ContractDaoInterface database) {
        this.contractDaoImpl = database;
    }

    public List<Contract> getContractList() {
        return contractDaoImpl.getContractList();
    }

}
