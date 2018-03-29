package lv.javaguru.java2.views.client;

import lv.javaguru.java2.businesslogic.client.removeclient.RemoveClientResponse;
import lv.javaguru.java2.businesslogic.client.removeclient.RemoveClientService;
import lv.javaguru.java2.domens.Client;
import lv.javaguru.java2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveClientView implements View {

    @Autowired
    private RemoveClientService removeClientService;
//    public RemoveClientView(ClientDaoInterface database) {
//        RemoveClientValidator removeClientValidator = new RemoveClientValidator(database);
//        this.removeClientService = new RemoveClientService(database, removeClientValidator);
//    }
    @Override
    public void execute() {
        System.out.println();
        System.out.println("Remove client from list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter client Id:");
        Integer id = Integer.parseInt(sc.nextLine());
        Client client = new Client(id, "personalCode", "name", "address", "eMail");
        RemoveClientResponse response = removeClientService.removeClient(client);
        if (response.isSuccess()) {
            System.out.println("Client successfully removed from list!");
            System.out.println();
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("Error field = " + error.getField());
                System.out.println("Error message = " + error.getErrorMessage());
            });
        }
        System.out.println("Remove client from list execution end!");
        System.out.println();
    }
}
