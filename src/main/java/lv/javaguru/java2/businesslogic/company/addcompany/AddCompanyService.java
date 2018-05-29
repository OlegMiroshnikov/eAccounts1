package lv.javaguru.java2.businesslogic.company.addcompany;

import lv.javaguru.java2.dao.CompanyDao;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.javaguru.java2.domain.builders.CompanyBuilder.createCompany;

@Component
public class AddCompanyService {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private AddCompanyValidator addCompanyValidator;

    @Transactional
    public AddCompanyResponse addCompany(AddCompanyRequest request) {
        List<Error> errors = addCompanyValidator.validate(request);
        if (!errors.isEmpty()) {
            return new AddCompanyResponse(errors);
        }
        Company company = createCompany()
                .withRegNr(request.getRegNr())
                .withName(request.getName())
                .withAddress(request.getAddress())
                .withEMail(request.getEMail())
                .withPathFromAccounts(request.getPathFromAccounts())
                .withPathToAccounts(request.getPathToAccounts())
                .build();
        companyDao.addCompany(company);
        return new AddCompanyResponse(company.getId());
    }
}
