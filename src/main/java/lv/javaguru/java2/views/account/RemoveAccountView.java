package lv.javaguru.java2.views.account;

import lv.javaguru.java2.businesslogic.account.removeaccount.RemoveAccountRequest;
import lv.javaguru.java2.businesslogic.account.removeaccount.RemoveAccountResponse;
import lv.javaguru.java2.businesslogic.account.removeaccount.RemoveAccountService;
import lv.javaguru.java2.domain.Account;
import lv.javaguru.java2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveAccountView implements View {

    @Autowired
    private RemoveAccountService removeAccountService;

//    public RemoveAccountView(AccountDao database) {
//        RemoveAccountValidator removeAccountValidator = new RemoveAccountValidator(database);
//        this.removeAccountService = new RemoveAccountService(database, removeAccountValidator);
//    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Remove account from list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter account Id:");
        Long id = Long.parseLong(sc.nextLine());

        RemoveAccountRequest request = new RemoveAccountRequest(id);
        RemoveAccountResponse response = removeAccountService.removeAccount(request);
        if (response.isSuccess()) {
            System.out.println("account successfully removed from list!");
            System.out.println();
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("Error field = " + error.getField());
                System.out.println("Error message = " + error.getErrorMessage());
            });
        }
        System.out.println("Remove account from list execution end!");
        System.out.println();
    }
}
