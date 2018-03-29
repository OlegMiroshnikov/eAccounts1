package lv.javaguru.java2.businesslogic.contract.addcontract;

import lv.javaguru.java2.dao.ContractDaoInterface;
import lv.javaguru.java2.domens.Contract;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddContractValidator {

    private ContractDaoInterface contractDaoImpl;

    public AddContractValidator(ContractDaoInterface database) {
        this.contractDaoImpl = database;
    }

    public List<Error> validate(Contract contract) {
        List<Error> errors = new ArrayList<>();
        validateCompanyID(contract.getCompanyId()).ifPresent(errors::add);
        validateClientID(contract.getClientId()).ifPresent(errors::add);
        validateDublicateContractBetweenCompanyAndClient(contract.getCompanyId(), contract.getClientId()).ifPresent(errors::add);
        return errors;
    }

    private Optional<Error> validateCompanyID(Integer companyID) {
        if (companyID == null) {
            return Optional.of(new Error("companyId", "Must not be null"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateClientID(Integer clientID) {
        if (clientID == null) {
            return Optional.of(new Error("clientId", "Must not be null"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateDublicateContractBetweenCompanyAndClient(Integer companyId, Integer clientId) {
        if (companyId != null && clientId != null) {
            Optional<Contract> contract = contractDaoImpl.getContractByCompanyIdAndClientId(companyId, clientId);
            if (contract.isPresent()) {
                return Optional.of(new Error("", "The contract between the company and the customer already exists"));
            }
        }
        return Optional.empty();
    }

}
