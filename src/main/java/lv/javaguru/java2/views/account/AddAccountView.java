package lv.javaguru.java2.views.account;

import lv.javaguru.java2.businesslogic.account.addaccount.AddAccountRequest;
import lv.javaguru.java2.businesslogic.account.addaccount.AddAccountResponse;
import lv.javaguru.java2.businesslogic.account.addaccount.AddAccountService;
import lv.javaguru.java2.domain.Account;
import lv.javaguru.java2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddAccountView implements View {

    @Autowired
    private AddAccountService addAccountService;

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Add account to list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter contact Id:");
        Integer contractId = Integer.parseInt(sc.nextLine());
        System.out.print("Enter file name of the account:");
        String fileName = sc.nextLine();

        AddAccountRequest request = new AddAccountRequest(contractId, fileName);
        AddAccountResponse response = addAccountService.addAccount(request);
        if (response.isSuccess()) {
            System.out.println("Account successfully added to list!");
            System.out.println();
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("Error field = " + error.getField());
                System.out.println("Error message = " + error.getErrorMessage());
            });
        }
        System.out.println("Add account to list execution end!");
        System.out.println();
    }
}
