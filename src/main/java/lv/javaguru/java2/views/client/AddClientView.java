package lv.javaguru.java2.views.client;

import lv.javaguru.java2.domens.Client;
import lv.javaguru.java2.businesslogic.client.addclient.AddClientService;
import lv.javaguru.java2.businesslogic.client.addclient.AddClientResponse;
import lv.javaguru.java2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddClientView  implements View {

    @Autowired
    private AddClientService addClientService;

//    public AddClientView(ClientDaoInterface database) {
//        AddClientValidator addClientValidator = new AddClientValidator(database);
//        this.addClientService = new AddClientService(database, addClientValidator);
//    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Add client to list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter client personal code:");
        String personalCode = sc.nextLine();
        System.out.print("Enter client name:");
        String name = sc.nextLine();
        System.out.print("Enter client address:");
        String address = sc.nextLine();
        System.out.print("Enter client eMail:");
        String eMail = sc.nextLine();
        Client client = new Client(personalCode, name,address, eMail);

        AddClientResponse response = addClientService.addClient(client);
        if (response.isSuccess()) {
            System.out.println("Client successfully added to list!");
            System.out.println();
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("Error field = " + error.getField());
                System.out.println("Error message = " + error.getErrorMessage());
            });
        }
        System.out.println("Add client to list execution end!");
        System.out.println();
    }

}
