package lv.javaguru.java2.views.contract;

import lv.javaguru.java2.businesslogic.contract.getcontractlist.GetContractListService;
import lv.javaguru.java2.domens.Contract;
import lv.javaguru.java2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractListView implements View{

    @Autowired
    private GetContractListService service;

//    public ContractListView(ContractDaoInterface database) {
//        this.service = new GetContractListService(database);
//    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Print contract list to console execution start!");
        for (Contract contract : service.getContractList()) {
            System.out.println(contract);
        }
        System.out.println("Print contract list to console execution end!");
        System.out.println();
    }
}
