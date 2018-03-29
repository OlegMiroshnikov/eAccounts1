package lv.javaguru.java2.views.company;

import lv.javaguru.java2.businesslogic.company.addcompany.AddCompanyResponse;
import lv.javaguru.java2.businesslogic.company.addcompany.AddCompanyService;
import lv.javaguru.java2.domens.Company;
import lv.javaguru.java2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddCompanyView implements View{

    @Autowired
    private AddCompanyService addCompanyService;

//    public AddCompanyView(CompanyDaoInterface database) {
//        AddCompanyValidator validator = new AddCompanyValidator(database);
//        this.addCompanyService = new AddCompanyService(database, validator);
//    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Add company to list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter company registration number:");
        String regNr = sc.nextLine();
        System.out.print("Enter company name:");
        String name = sc.nextLine();
        System.out.print("Enter company address:");
        String address = sc.nextLine();
        System.out.print("Enter company eMail:");
        String eMail = sc.nextLine();
        System.out.print("Enter path from which company accounts are exported:");
        String pathFromAccounts = sc.nextLine();
        System.out.print("Enter path to which company accounts are exported:");
        String pathToAccounts = sc.nextLine();

        Company company = new Company(regNr, name,address, eMail, pathFromAccounts, pathToAccounts);

        AddCompanyResponse response = addCompanyService.addCompany(company);
        if (response.isSuccess()) {
            System.out.println("Company successfully added to list!");
            System.out.println();
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("Error field = " + error.getField());
                System.out.println("Error message = " + error.getErrorMessage());
            });
        }
        System.out.println("Add company to list execution end!");
        System.out.println();
    }
}

