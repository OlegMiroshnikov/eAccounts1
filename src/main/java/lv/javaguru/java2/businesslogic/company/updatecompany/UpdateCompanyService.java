package lv.javaguru.java2.businesslogic.company.updatecompany;

import lv.javaguru.java2.dao.CompanyDao;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.javaguru.java2.domain.builders.CompanyBuilder.createCompany;

@Component
public class UpdateCompanyService {
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private UpdateCompanyValidator updateCompanyValidator;

    @Transactional
    public UpdateCompanyResponse updateCompany(UpdateCompanyRequest request) {
        List<Error> errors = updateCompanyValidator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateCompanyResponse(false, errors);
        }
        Company company = createCompany()
                .withId(request.getId())
                .withRegNr(request.getRegNr())
                .withName(request.getName())
                .withAddress(request.getAddress())
                .withEMail(request.geteMail())
                .withPathFromAccounts(request.getPathFromAccounts())
                .withPathToAccounts((request.getPathToAccounts()))
                .build();
        companyDao.updateCompany(company);
        return new UpdateCompanyResponse(true, null);
    }
}
