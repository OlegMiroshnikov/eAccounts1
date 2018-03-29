package lv.javaguru.java2.businesslogic.company.addcompany;

import lv.javaguru.java2.dao.CompanyDaoInterface;
import lv.javaguru.java2.domens.Company;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddCompanyService {

    private CompanyDaoInterface companyDaoImpl;
    private AddCompanyValidator addCompanyValidator;

    public AddCompanyService(CompanyDaoInterface database, AddCompanyValidator addCompanyValidator) {
        this.companyDaoImpl = database;
        this.addCompanyValidator = addCompanyValidator;
    }

    public AddCompanyResponse addCompany(Company company) {
        List<Error> errors = addCompanyValidator.validate(company);
        if (!errors.isEmpty()) {
            return new AddCompanyResponse(false, errors);
        }
        companyDaoImpl.addCompany(company);
        return new AddCompanyResponse(true, null);
    }
}
