package lv.javaguru.java2.businesslogic.account.updateaccount;

import lv.javaguru.java2.dao.AccountDao;
import lv.javaguru.java2.domain.Account;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.javaguru.java2.domain.builders.AccountBuilder.createAccount;

@Component
public class UpdateAccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private UpdateAccountValidator updateAccountValidator;

    @Transactional
    public UpdateAccountResponse updateAccount(UpdateAccountRequest request) {
        List<Error> errors = updateAccountValidator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateAccountResponse(false, errors);
        }
        Account account = createAccount()
                .withId(request.getId())
                .withContractId(request.getContractId())
                .withFileName(request.getFileName())
                .withDateSendind(request.getDateSending())
                .withDateViewing(request.getDateViewing())
                .withDateReminderSendind(request.getDateReminderSending())
                .build();
        accountDao.updateAccount(account);
        return new UpdateAccountResponse(true, null);
    }
}
