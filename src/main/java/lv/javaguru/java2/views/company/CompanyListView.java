package lv.javaguru.java2.views.company;

import lv.javaguru.java2.businesslogic.company.getcompanylist.GetCompanyListService;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyListView implements View{

    @Autowired
    private GetCompanyListService service;

//    public CompanyListView(CompanyDao database) {
//        this.service = new GetCompanyListService(database);
//    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Print company list to console execution start!");
        for (Company company : service.getCompanyList()) {
            System.out.println(company);
        }
        System.out.println("Print company list to console execution end!");
        System.out.println();
    }
}

