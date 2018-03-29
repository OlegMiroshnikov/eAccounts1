package lv.javaguru.java2.views.contract;

import lv.javaguru.java2.businesslogic.contract.addcontract.AddContractResponse;
import lv.javaguru.java2.businesslogic.contract.addcontract.AddContractService;
import lv.javaguru.java2.domens.Contract;
import lv.javaguru.java2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
public class AddContractView implements View {

    @Autowired
    private AddContractService addContractService;

//    public AddContractView(ContractDaoInterface database) {
//        AddContractValidator validator = new AddContractValidator(database);
//        this.addContractService = new AddContractService(database, validator);
//    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Add contract to list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter company Id:");
        Integer companyId = Integer.parseInt(sc.nextLine());
        System.out.print("Enter client Id:");
        Integer clientId = Integer.parseInt(sc.nextLine());
        System.out.print("Enter contract number:");
        String number = sc.nextLine();
        System.out.print("Enter date of signing the contract:");
        String dateSignStr = sc.nextLine();
        LocalDate dateSign;
        if (dateSignStr.isEmpty()){
            dateSign = LocalDate.now();
        } else {
            dateSign = LocalDate.parse(dateSignStr);
        }
        System.out.print("Enter date of the begin of contract:");
        String dateBeginStr = sc.nextLine();
        LocalDate dateBegin;
        if (dateBeginStr.isEmpty()){
            dateBegin = LocalDate.now();
        } else {
            dateBegin = LocalDate.parse(dateBeginStr);
        }
        System.out.print("Enter date of the end of contract:");
        String dateEndStr = sc.nextLine();
        LocalDate dateEnd;
        if (dateEndStr.isEmpty()){
            dateEnd = LocalDate.now();
        } else {
            dateEnd = LocalDate.parse(dateEndStr);
        }
        System.out.print("Enter day to sending the account to client:");
        Integer dayToSendAccount = Integer.parseInt(sc.nextLine());
        System.out.print("Enter number of days to sending reminder to client:");
        Integer daysToSendReminder = Integer.parseInt(sc.nextLine());
        System.out.print("Enter status of the contract:");
        Integer status = Integer.parseInt(sc.nextLine());

        Contract contract = new Contract(companyId, clientId, number, dateSign, dateBegin,
                dateEnd, dayToSendAccount, daysToSendReminder, status);

        AddContractResponse response = addContractService.addContract(contract);
        if (response.isSuccess()) {
            System.out.println("Contract successfully added to list!");
            System.out.println();
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("Error field = " + error.getField());
                System.out.println("Error message = " + error.getErrorMessage());
            });
        }
        System.out.println("Add contract to list execution end!");
        System.out.println();
    }
}
