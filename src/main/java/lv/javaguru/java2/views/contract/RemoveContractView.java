package lv.javaguru.java2.views.contract;

import lv.javaguru.java2.businesslogic.contract.removecontract.RemoveContractRequest;
import lv.javaguru.java2.businesslogic.contract.removecontract.RemoveContractResponse;
import lv.javaguru.java2.businesslogic.contract.removecontract.RemoveContractService;
import lv.javaguru.java2.domain.Contract;
import lv.javaguru.java2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveContractView implements View {

    @Autowired
    private RemoveContractService removeContractService;

//    public RemoveContractView(ContractDao database) {
//        RemoveContractValidator removeContractValidator = new RemoveContractValidator(database);
//        this.removeContractService = new RemoveContractService(database, removeContractValidator);
//    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Remove contract from list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter contract Id:");
        Integer id = Integer.parseInt(sc.nextLine());

        RemoveContractRequest request = new RemoveContractRequest(id);
        RemoveContractResponse response = removeContractService.removeContract(request);
        if (response.isSuccess()) {
            System.out.println("Contract successfully removed from list!");
            System.out.println();
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("Error field = " + error.getField());
                System.out.println("Error message = " + error.getErrorMessage());
            });
        }
        System.out.println("Remove contract from list execution end!");
        System.out.println();
    }
}
