package lv.javaguru.java2.businesslogic.company.removecompany;

import lv.javaguru.java2.dao.CompanyDaoInterface;
import lv.javaguru.java2.domens.Company;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveCompanyService {

    private CompanyDaoInterface companyDaoImpl;
    private RemoveCompanyValidator removeCompanyValidator;

    public RemoveCompanyService(CompanyDaoInterface database, RemoveCompanyValidator removeCompanyValidator) {
        this.companyDaoImpl = database;
        this.removeCompanyValidator = removeCompanyValidator;
    }

    public RemoveCompanyResponse removeCompany(Company company) {
        List<Error> errors = removeCompanyValidator.validate(company);
        if (!errors.isEmpty()) {
            return new RemoveCompanyResponse(false, errors);
        }
        companyDaoImpl.removeCompany(company);
        return new RemoveCompanyResponse(true, null);
    }
}
