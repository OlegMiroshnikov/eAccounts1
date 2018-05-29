package lv.javaguru.java2.views.company;

import lv.javaguru.java2.businesslogic.company.removecompany.RemoveCompanyRequest;
import lv.javaguru.java2.businesslogic.company.removecompany.RemoveCompanyResponse;
import lv.javaguru.java2.businesslogic.company.removecompany.RemoveCompanyService;
import lv.javaguru.java2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveCompanyView implements View {

    @Autowired
    private RemoveCompanyService removeCompanyService;

//    public RemoveCompanyView(CompanyDao database) {
//        RemoveCompanyValidator removeCompanyValidator = new RemoveCompanyValidator(database);
//        this.removeCompanyService = new RemoveCompanyService(database, removeCompanyValidator);
//    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Remove company from list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter company Id:");
        Integer id = Integer.parseInt(sc.nextLine());

        RemoveCompanyRequest request = new RemoveCompanyRequest(id);
        RemoveCompanyResponse response = removeCompanyService.removeCompany(request);
        if (response.isSuccess()) {
            System.out.println("Company successfully removed from list!");
            System.out.println();
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("Error field = " + error.getField());
                System.out.println("Error message = " + error.getErrorMessage());
            });
        }
        System.out.println("Remove company from list execution end!");
        System.out.println();
    }
}