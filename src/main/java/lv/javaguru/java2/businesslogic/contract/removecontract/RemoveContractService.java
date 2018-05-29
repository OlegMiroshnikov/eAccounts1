package lv.javaguru.java2.businesslogic.contract.removecontract;

import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Contract;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.javaguru.java2.domain.builders.ContractBuilder.createContract;

@Component
public class RemoveContractService {

    @Autowired
    private ContractDao contractDao;
    @Autowired
    private RemoveContractValidator removeContractValidator;

    @Transactional
    public RemoveContractResponse removeContract(RemoveContractRequest request) {
        List<Error> errors = removeContractValidator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveContractResponse(false, errors);
        }
        contractDao.removeContract(request.getId());
        return new RemoveContractResponse(true, null);
    }

}
