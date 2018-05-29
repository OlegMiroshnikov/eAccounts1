package lv.javaguru.java2.businesslogic.company.updatecompany;

import lv.javaguru.java2.dao.CompanyDao;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateCompanyValidator {

    @Autowired
    private CompanyDao companyDao;

    public List<Error> validate(UpdateCompanyRequest request) {
        List<Error> errors = new ArrayList<>();
        validateId(request.getId()).ifPresent(errors::add);
        validateIsExistCompanyById(request.getId()).ifPresent(errors::add);
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

}
