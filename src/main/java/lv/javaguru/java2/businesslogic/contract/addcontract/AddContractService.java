package lv.javaguru.java2.businesslogic.contract.addcontract;

import lv.javaguru.java2.dao.ContractDaoInterface;
import lv.javaguru.java2.domens.Contract;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddContractService {

    private ContractDaoInterface contractDaoImpl;
    private AddContractValidator addContractValidator;

    public AddContractService(ContractDaoInterface database, AddContractValidator addContractValidator) {
        this.contractDaoImpl = database;
        this.addContractValidator = addContractValidator;
    }

    public AddContractResponse addContract(Contract contract) {
        List<Error> errors = addContractValidator.validate(contract);
        if (!errors.isEmpty()) {
            return new AddContractResponse(false, errors);
        }
        contractDaoImpl.addContract(contract);
        return new AddContractResponse(true, null);
    }

}
