package lv.javaguru.java2.businesslogic.contract.updatecontract;

import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Contract;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateContractValidator {
    @Autowired
    private ContractDao contractDao;

    public List<Error> validate(UpdateContractRequest request) {
        List<Error> errors = new ArrayList<>();
        validateId(request.getId()).ifPresent(errors::add);
        validateIsExistContractById(request.getId()).ifPresent(errors::add);
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
            Optional<Contract> foundContract = contractDao.getContractById(id);
            if (!foundContract.isPresent()) {
                return Optional.of(new Error("id", "Contract by id not exist"));
            }
        }
        return Optional.empty();
    }

}
