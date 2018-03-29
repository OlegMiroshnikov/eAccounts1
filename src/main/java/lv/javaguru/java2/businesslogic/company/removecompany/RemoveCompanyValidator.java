package lv.javaguru.java2.businesslogic.company.removecompany;

import lv.javaguru.java2.dao.CompanyDaoInterface;
import lv.javaguru.java2.domens.Company;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveCompanyValidator {

    private CompanyDaoInterface companyDaoImpl;

    public RemoveCompanyValidator(CompanyDaoInterface database) {
        this.companyDaoImpl = database;
    }

    public List<Error> validate(Company company) {
        List<Error> errors = new ArrayList<>();
        validateId(company.getId()).ifPresent(errors::add);
        validateIsExistCompanyById(company.getId()).ifPresent(errors::add);
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
            Optional<Company> foundCompany = companyDaoImpl.getCompanyById(id);
            if (!foundCompany.isPresent()) {
                return Optional.of(new Error("id", "Company by id not exist"));
            }
        }
        return Optional.empty();
    }

}
