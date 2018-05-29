package lv.javaguru.java2.businesslogic.company.getcompany;

import lv.javaguru.java2.dao.CompanyDao;
import lv.javaguru.java2.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class GetCompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Transactional
    public Optional<Company> getCompanyById(Integer id) {
        return companyDao.getCompanyById(id);
    }
}
