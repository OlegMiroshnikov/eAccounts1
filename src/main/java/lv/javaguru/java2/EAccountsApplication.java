package lv.javaguru.java2;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.dao.*;
import lv.javaguru.java2.views.*;
import lv.javaguru.java2.views.account.AccountListView;
import lv.javaguru.java2.views.account.AddAccountView;
import lv.javaguru.java2.views.account.RemoveAccountView;
import lv.javaguru.java2.views.company.AddCompanyView;
import lv.javaguru.java2.views.company.CompanyListView;
import lv.javaguru.java2.views.company.RemoveCompanyView;
import lv.javaguru.java2.views.client.AddClientView;
import lv.javaguru.java2.views.client.ClientsListView;
import lv.javaguru.java2.views.client.RemoveClientView;
import lv.javaguru.java2.views.contract.AddContractView;
import lv.javaguru.java2.views.contract.ContractListView;
import lv.javaguru.java2.views.contract.RemoveContractView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EAccountsApplication {
    public static void main(String[] args) {

        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(SpringAppConfig.class);

//        ClientDao clientDatabase = new ClientDaoMemoryDatabaseImpl();
//        ClientDao clientDatabase = new ClientDaoJDBCImpl();
//        CompanyDao companyDatabase = new CompanyDaoMemoryDatabaseImpl();
//        ContractDao contractDatabase = new ContractDaoMemoryDatabaseImpl();
//        AccountDao accountDatabase = new AccountDaoMemoryDatabaseImpl();

//        View addCompanyView = new AddCompanyView(companyDatabase);
//        View removeCompanyView = new RemoveCompanyView(companyDatabase);
//        View companyListView = new CompanyListView(companyDatabase);
//
//        View addClientView = new AddClientView(clientDatabase);
//        View removeClientView = new RemoveClientView(clientDatabase);
//        View clientsListView = new ClientsListView(clientDatabase);
//
//        View addContractView = new AddContractView(contractDatabase);
//        View removeContractView = new RemoveContractView(contractDatabase);
//        View contractListView = new ContractListView(contractDatabase);
//
////        View addAccountView = new AddAccountView(accountDatabase);
//        View addAccountView = new AddAccountView(clientDatabase, companyDatabase, contractDatabase, accountDatabase);
//        View removeAccountView = new RemoveAccountView(accountDatabase);
//        View accountListView = new AccountListView(accountDatabase);

        Map<Integer, View> actionMap = new HashMap<>();

        actionMap.put(11, applicationContext.getBean(AddCompanyView.class));
        actionMap.put(12, applicationContext.getBean(RemoveCompanyView.class));
        actionMap.put(13, applicationContext.getBean(CompanyListView.class));

        actionMap.put(21, applicationContext.getBean(AddClientView.class));
        actionMap.put(22, applicationContext.getBean(RemoveClientView.class));
        actionMap.put(23, applicationContext.getBean(ClientsListView.class));

        actionMap.put(31, applicationContext.getBean(AddContractView.class));
        actionMap.put(32, applicationContext.getBean(RemoveContractView.class));
        actionMap.put(33, applicationContext.getBean(ContractListView.class));

        actionMap.put(41, applicationContext.getBean(AddAccountView.class));
        actionMap.put(42, applicationContext.getBean(RemoveAccountView.class));
        actionMap.put(43, applicationContext.getBean(AccountListView.class));

        while (true) {
            printProgramMainMenu();
            int mainMenuItem = getFromUserMenuItemToExecute();
            if (mainMenuItem < 5) {
                manageToLocalMenu(actionMap, mainMenuItem);
            } else {
                System.out.println("Goodbye, thank you!");
                break;
            }
        }
    }

    private static void printProgramMainMenu() {
        System.out.println("");
        System.out.println("SYSTEM 'eACCOUNT' MAIN MENU:");
        System.out.println("1. COMPANIES");
        System.out.println("2. CLIENTS");
        System.out.println("3. CONTRACTS");
        System.out.println("4. ACCOUNTS");
        System.out.println("5. Exit");
    }

    private static void printProgramCompaniesMenu() {
        System.out.println("COMPANIES services menu:");
        System.out.println("1. Add company to list");
        System.out.println("2. Delete company from list");
        System.out.println("3. Print company list to console");
        System.out.println("4. Exit");
    }

    private static void printProgramClientsMenu() {
        System.out.println("CLIENTS services menu:");
        System.out.println("1. Add client to list");
        System.out.println("2. Delete client from list");
        System.out.println("3. Print client list to console");
        System.out.println("4. Exit");
    }

    private static void printProgramContractsMenu() {
        System.out.println("CONTRACTS services menu:");
        System.out.println("1. Add contract to list");
        System.out.println("2. Delete contract from list");
        System.out.println("3. Print contract list to console");
        System.out.println("4. Exit");
    }

    private static void printProgramAccountsMenu() {
        System.out.println("ACCOUNTS services menu:");
        System.out.println("1. Add account to list");
        System.out.println("2. Delete account from list");
        System.out.println("3. Print account list to console");
        System.out.println("4. Exit");
    }

    private static int getFromUserMenuItemToExecute() {
        System.out.print("Please, enter menu item number to execute:");
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        return Integer.parseInt(sc.nextLine());
    }

    private static void manageToLocalMenu(Map<Integer, View> actionMap, int mainMenuItem) {
        while (true) {
            printProgramLocalMenu(mainMenuItem);
            int localMenuItem = getFromUserMenuItemToExecute();
            if (localMenuItem > 3) break;
            View view = actionMap.get(10 * mainMenuItem + localMenuItem);
            view.execute();
        }
    }

    private static void printProgramLocalMenu(int mainMenuItem) {
        System.out.println("");
        if (mainMenuItem == 1) printProgramCompaniesMenu();
        if (mainMenuItem == 2) printProgramClientsMenu();
        if (mainMenuItem == 3) printProgramContractsMenu();
        if (mainMenuItem == 4) printProgramAccountsMenu();
    }

}
