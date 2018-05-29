package lv.javaguru.java2.businesslogic.company.removecompany;

import lv.javaguru.java2.dao.CompanyDao;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.javaguru.java2.domain.builders.CompanyBuilder.createCompany;

@Component
public class RemoveCompanyService {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private RemoveCompanyValidator removeCompanyValidator;

    @Transactional
    public RemoveCompanyResponse removeCompany(RemoveCompanyRequest request) {
        List<Error> errors = removeCompanyValidator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveCompanyResponse(false, errors);
        }
        companyDao.removeCompany(request.getId());
        return new RemoveCompanyResponse(true, null);
    }
}
