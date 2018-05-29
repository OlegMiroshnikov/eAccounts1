package lv.javaguru.java2.businesslogic.contract.addcontract;

import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Contract;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.javaguru.java2.domain.builders.ContractBuilder.createContract;

@Component
public class AddContractService {

    @Autowired
    private ContractDao contractDao;
    @Autowired
    private AddContractValidator addContractValidator;

    @Transactional
    public AddContractResponse addContract(AddContractRequest request) {
        List<Error> errors = addContractValidator.validate(request);
        if (!errors.isEmpty()) {
            return new AddContractResponse(null, false, errors);
        }
        Contract contract = createContract()
                .withCompanyId(request.getCompanyId())
                .withClientId(request.getClientId())
                .withNumber(request.getNumber())
                .withDateSign(request.getDateSign())
                .withDateBegin(request.getDateBegin())
                .withDateEnd(request.getDateEnd())
                .withDayToSendAccount(request.getDayToSendAccount())
                .withCountDaysToSendReminder(request.getCountDaysToSendReminder())
                .withStatus(request.getStatus())
                .build();
        contractDao.addContract(contract);
        return new AddContractResponse(contract.getId(), true, null);
    }

}
