package lv.javaguru.java2.businesslogic.company.addcompany;

import lv.javaguru.java2.dao.CompanyDao;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddCompanyValidator {

    @Autowired
    private CompanyDao companyDao;

    public List<Error> validate(AddCompanyRequest request) {
        List<Error> errors = new ArrayList<>();
        validateRegNr(request.getRegNr()).ifPresent(errors::add);
        validateName(request.getName()).ifPresent(errors::add);
        validateEMail(request.getEMail()).ifPresent(errors::add);
        validatePathFromAccounts(request.getPathFromAccounts()).ifPresent(errors::add);
        validatePathToAccounts(request.getPathToAccounts()).ifPresent(errors::add);
        validateDuplicateRegNr(request.getRegNr()).ifPresent(errors::add);
        return errors;
    }

    private Optional<Error> validateRegNr(String regNr) {
        if (regNr == null || regNr.trim().isEmpty()) {
            return Optional.of(new Error("regNr", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Optional.of(new Error("name", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateEMail(String eMail) {
        if (eMail == null || eMail.trim().isEmpty()) {
            return Optional.of(new Error("eMail", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validatePathFromAccounts(String pathFromAccounts) {
        if (pathFromAccounts == null || pathFromAccounts.trim().isEmpty()) {
            return Optional.of(new Error("pathFromAccounts", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validatePathToAccounts(String pathToAccounts) {
        if (pathToAccounts == null || pathToAccounts.trim().isEmpty()) {
            return Optional.of(new Error("pathToAccounts", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateDuplicateRegNr(String regNr) {
        if (regNr != null && !regNr.trim().isEmpty()) {
            Optional<Company> company = companyDao.getCompanyByRegNr(regNr);
            if (company.isPresent()) {
                return Optional.of(new Error("regNr", "Must not be repeated"));
            }
        }
        return Optional.empty();
    }

}
