package lv.javaguru.java2.businesslogic.contract.removecontract;

import lv.javaguru.java2.dao.ContractDaoInterface;
import lv.javaguru.java2.domens.Contract;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveContractService {

    private ContractDaoInterface contractDaoImpl;
    private RemoveContractValidator removeContractValidator;

    public RemoveContractService(ContractDaoInterface database, RemoveContractValidator removeContractValidator) {
        this.contractDaoImpl = database;
        this.removeContractValidator = removeContractValidator;
    }

    public RemoveContractResponse removeContract(Contract contract) {
        List<Error> errors = removeContractValidator.validate(contract);
        if (!errors.isEmpty()) {
            return new RemoveContractResponse(false, errors);
        }
        contractDaoImpl.removeContract(contract);
        return new RemoveContractResponse(true, null);
    }

}
