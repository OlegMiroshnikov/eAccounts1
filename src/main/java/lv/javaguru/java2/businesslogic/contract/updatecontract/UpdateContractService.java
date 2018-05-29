package lv.javaguru.java2.businesslogic.contract.updatecontract;

import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Contract;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.javaguru.java2.domain.builders.ContractBuilder.createContract;

@Component
public class UpdateContractService {
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private UpdateContractValidator updateContractValidator;

    @Transactional
    public UpdateContractResponse updateContract(UpdateContractRequest request) {
        List<Error> errors = updateContractValidator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateContractResponse(false, errors);
        }
        Contract contract = createContract()
                .withId(request.getId())
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
        contractDao.updateContract(contract);
        return new UpdateContractResponse(true, null);
    }

}
