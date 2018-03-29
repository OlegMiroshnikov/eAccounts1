package lv.javaguru.java2.businesslogic.contract.removecontract;

import lv.javaguru.java2.dao.ContractDaoInterface;
import lv.javaguru.java2.domens.Contract;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveContractValidator {

    private ContractDaoInterface contractDaoImpl;

    public RemoveContractValidator(ContractDaoInterface database) {
        this.contractDaoImpl = database;
    }

    public List<Error> validate(Contract contract) {
        List<Error> errors = new ArrayList<>();
        validateId(contract.getId()).ifPresent(errors::add);
        validateIsExistContractById(contract.getId()).ifPresent(errors::add);
        return errors;
    }

    private Optional<Error> validateId(Integer id) {
        if (id == null) {
            return Optional.of(new Error("id", "Must not be null"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateIsExistContractById(Integer id) {
        if (id != null) {
            Optional<Contract> foundContract = contractDaoImpl.getContractById(id);
            if (!foundContract.isPresent()) {
                return Optional.of(new Error("id", "Contract by id not exist"));
            }
        }
        return Optional.empty();
    }

}
