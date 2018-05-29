package lv.javaguru.java2.businesslogic.account.addaccount;

import lv.javaguru.java2.dao.AccountDao;
import lv.javaguru.java2.domain.Account;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static lv.javaguru.java2.domain.builders.AccountBuilder.createAccount;

@Component
public class AddAccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private AddAccountValidator addAccountValidator;

    @Transactional
    public AddAccountResponse addAccount(AddAccountRequest request) {
        List<Error> errors = addAccountValidator.validate(request);
        if (!errors.isEmpty()) {
            return new AddAccountResponse(errors);
        }
        Account account = createAccount()
                .withContractId(request.getContractId())
                .withFileName(request.getFileName())
                .withDateSendind(request.getDateSending())
                .withDateViewing(request.getDateViewing())
                .withDateReminderSendind(request.getDateReminderSending())
                .build();
        accountDao.addAccount(account);
        return new AddAccountResponse(account.getId());
    }
}
