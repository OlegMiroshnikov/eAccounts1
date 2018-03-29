package lv.javaguru.java2.businesslogic.company.getcompanylist;

import lv.javaguru.java2.dao.CompanyDaoInterface;
import lv.javaguru.java2.domens.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetCompanyListService {

    private CompanyDaoInterface companyDaoImpl;

    public GetCompanyListService(CompanyDaoInterface database) {
        this.companyDaoImpl = database;
    }

    public List<Company> getCompanyList() {
        return companyDaoImpl.getCompanyList();
    }
}
