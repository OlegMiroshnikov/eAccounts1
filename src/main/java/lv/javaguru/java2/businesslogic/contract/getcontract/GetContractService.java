package lv.javaguru.java2.businesslogic.contract.getcontract;

import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class GetContractService {

    @Autowired
    private ContractDao contractDao;

    @Transactional
    public Optional<Contract> getContractById(Integer id) {
        return contractDao.getContractById(id);
    }
}

