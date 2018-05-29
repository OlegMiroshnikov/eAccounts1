package lv.javaguru.java2.views.account;

import lv.javaguru.java2.businesslogic.account.getaccountlist.GetAccountListService;
import lv.javaguru.java2.domain.Account;
import lv.javaguru.java2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountListView implements View{

    @Autowired
    private GetAccountListService service;

//    public AccountListView (AccountDao database) {
//        this.service = new GetAccountListService(database);
//    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Print account list to console execution start!");
        for (Account account : service.getAccountList()) {
            System.out.println(account);
        }
        System.out.println("Print account list to console execution end!");
        System.out.println();
    }
}
