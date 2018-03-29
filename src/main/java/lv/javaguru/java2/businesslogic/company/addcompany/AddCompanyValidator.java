package lv.javaguru.java2.businesslogic.company.addcompany;

import lv.javaguru.java2.dao.CompanyDaoInterface;
import lv.javaguru.java2.domens.Company;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddCompanyValidator {

    private CompanyDaoInterface companyDaoImpl;

    public AddCompanyValidator(CompanyDaoInterface database) {
        this.companyDaoImpl = database;
    }

    public List<Error> validate(Company company) {
        List<Error> errors = new ArrayList<>();
        validateRegNr(company.getRegNr()).ifPresent(errors::add);
        validateName(company.getName()).ifPresent(errors::add);
        validateAddress(company.getAddress()).ifPresent(errors::add);
        validateEMail(company.getEMail()).ifPresent(errors::add);
        validatePathFromAccounts(company.getPathFromAccounts()).ifPresent(errors::add);
        validatePathToAccounts(company.getPathToAccounts()).ifPresent(errors::add);
        validateDuplicateRegNr(company.getRegNr()).ifPresent(errors::add);
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

    private Optional<Error> validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            return Optional.of(new Error("address", "Must not be empty"));
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
            Optional<Company> company = companyDaoImpl.getCompanyByRegNr(regNr);
            if (company.isPresent()) {
                return Optional.of(new Error("regNr", "Must not be repeated"));
            }
        }
        return Optional.empty();
    }

}
