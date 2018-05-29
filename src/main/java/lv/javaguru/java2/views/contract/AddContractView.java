package lv.javaguru.java2.views.contract;

import javassist.bytecode.stackmap.BasicBlock;
import lv.javaguru.java2.businesslogic.contract.addcontract.AddContractRequest;
import lv.javaguru.java2.businesslogic.contract.addcontract.AddContractResponse;
import lv.javaguru.java2.businesslogic.contract.addcontract.AddContractService;
import lv.javaguru.java2.domain.Contract;
import lv.javaguru.java2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

@Component
public class AddContractView implements View {

    @Autowired
    private AddContractService addContractService;

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
        Date dateSign = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
        if (!dateSignStr.isEmpty()){
            try {
                dateSign = format.parse(dateSignStr);
            }catch (ParseException ex) {}
        }
        System.out.print("Enter date of the begin of contract:");
        String dateBeginStr = sc.nextLine();
        Date dateBegin = new Date();
        if (!dateBeginStr.isEmpty()){
            try {
                dateBegin = format.parse(dateBeginStr);
            }catch (ParseException ex) {}
        }
        System.out.print("Enter date of the end of contract:");
        String dateEndStr = sc.nextLine();
        Date dateEnd = new Date();
        if (!dateEndStr.isEmpty()){
            try {
                dateEnd = format.parse(dateEndStr);
            }catch (ParseException ex) {}
        }
        System.out.print("Enter day to sending the account to client:");
        Integer dayToSendAccount = Integer.parseInt(sc.nextLine());
        System.out.print("Enter number of days to sending reminder to client:");
        Integer daysToSendReminder = Integer.parseInt(sc.nextLine());
        System.out.print("Enter status of the contract:");
        Integer status = Integer.parseInt(sc.nextLine());

        AddContractRequest request = new AddContractRequest(companyId, clientId, number, dateSign, dateBegin,
                dateEnd, dayToSendAccount, daysToSendReminder, status);

        AddContractResponse response = addContractService.addContract(request);
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
