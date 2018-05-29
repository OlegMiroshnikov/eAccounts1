package lv.javaguru.java2.businesslogic.company.getcompanylist;

import lv.javaguru.java2.dao.CompanyDao;
import lv.javaguru.java2.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetCompanyListService {

    @Autowired
    private CompanyDao companyDao;

    @Transactional
    public List<Company> getCompanyList() {
        return companyDao.getCompanyList();
    }
}
