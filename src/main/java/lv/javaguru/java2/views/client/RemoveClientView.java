package lv.javaguru.java2.views.client;

import lv.javaguru.java2.businesslogic.client.removeclient.RemoveClientRequest;
import lv.javaguru.java2.businesslogic.client.removeclient.RemoveClientResponse;
import lv.javaguru.java2.businesslogic.client.removeclient.RemoveClientService;
import lv.javaguru.java2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveClientView implements View {

    @Autowired
    private RemoveClientService removeClientService;

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Remove client from list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter client Id:");
        Integer id = Integer.parseInt(sc.nextLine());
        RemoveClientRequest request = new RemoveClientRequest(id);
        RemoveClientResponse response = removeClientService.removeClient(request);
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
