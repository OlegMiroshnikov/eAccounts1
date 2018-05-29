package lv.javaguru.java2.businesslogic.company.removecompany;

import lv.javaguru.java2.dao.CompanyDao;
import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.Contract;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveCompanyValidator {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private ContractDao contractDao;

    public List<Error> validate(RemoveCompanyRequest request) {
        List<Error> errors = new ArrayList<>();
        validateId(request.getId()).ifPresent(errors::add);
        validateIsExistCompanyById(request.getId()).ifPresent(errors::add);
        validateIsExistAnyContractByCompanyId(request.getId()).ifPresent(errors::add);
        return errors;
    }

    private Optional<Error> validateId(Integer id) {
        if (id == null) {
            return Optional.of(new Error("id", "Must not be null"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateIsExistCompanyById(Integer id) {
        if (id != null) {
            Optional<Company> foundCompany = companyDao.getCompanyById(id);
            if (!foundCompany.isPresent()) {
                return Optional.of(new Error("id", "Company by id not exist"));
            }
        }
        return Optional.empty();
    }

    private Optional<Error> validateIsExistAnyContractByCompanyId(Integer id) {
        if (id != null) {
            List<Contract> contracts = contractDao.getContractListByCompanyId(id);
            if (contracts.size() != 0) {
                return Optional.of(new Error("id", "There are also contracts with this company"));
            }
        }
        return Optional.empty();
    }
}
