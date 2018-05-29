package lv.javaguru.java2.businesslogic.contract.getcontractlist;

import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetContractListService {

    @Autowired
    private ContractDao contractDao;

    @Transactional
    public List<Contract> getContractList() {
        return contractDao.getContractList();
    }

}
