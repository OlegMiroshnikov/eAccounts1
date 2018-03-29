package lv.javaguru.java2.businesslogic.account.addaccount;

import lv.javaguru.java2.dao.AccountDaoInterface;
import lv.javaguru.java2.domens.Account;
import lv.javaguru.java2.domens.Company;
import lv.javaguru.java2.validators.Error;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddAccountValidator {
//    private ClientDaoInterface clientDaoImpl;
//    private CompanyDaoInterface companyDaoImpl;
//    private ContractDaoInterface contractDaoImpl;
    private AccountDaoInterface accountDaoImpl;


    public AddAccountValidator(AccountDaoInterface accountDaoImpl) {
        this.accountDaoImpl = accountDaoImpl;
    }

//    @Autowired
//    public AddAccountValidator(ClientDaoInterface clientDaoImpl, CompanyDaoInterface companyDaoImpl, ContractDaoInterface contractDaoImpl, AccountDaoInterface accountDaoImpl) {
//        this.clientDaoImpl = clientDaoImpl;
//        this.companyDaoImpl = companyDaoImpl;
//        this.contractDaoImpl = contractDaoImpl;
//        this.accountDaoImpl = accountDaoImpl;
//    }

    public List<Error> validate(Account account) {
        List<Error> errors = new ArrayList<>();
        validateContractID(account.getContractId()).ifPresent(errors::add);
        validateFileName(account.getFileName()).ifPresent(errors::add);
        validateFileExist(account.getFileName(), account.getContractId()).ifPresent(errors::add);
        return errors;
    }

    private Optional<Error> validateContractID(Integer contractID) {
        if (contractID == null) {
            return Optional.of(new Error("contractId", "Must not be null"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateFileName(String fileName) {
        if ((fileName == null) || (fileName.trim().isEmpty())) {
            return Optional.of(new Error("fileName", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

//    private Optional<Error> validateFileExist(String fileName, Integer contractId) {
//        if ((contractId != null) && (fileName != null) && (!fileName.trim().isEmpty())) {
//            Optional<Contract> contract = contractDaoImpl.getContractById(contractId);
//            if (contract.isPresent()) {
//                Optional<Company> company = companyDaoImpl.getCompanyById(contract.get().getCompanyId());
//                if (company.isPresent()) {
//                    String pathToAccounts = company.get().getPathToAccounts();
//                    if (!(pathToAccounts.trim().isEmpty())) {
//                        String pathName = pathToAccounts + "\\" + fileName + ".pdf";
//                        if (new File(pathName).exists()) {
//                            return Optional.empty();
//                        }
//                    }
//                }
//            }
//            return Optional.of(new Error("fileName", "Must be exist"));
//        }
//        return Optional.empty();
//    }

    private Optional<Error> validateFileExist(String fileName, Integer contractId) {
        if ((contractId != null) && (fileName != null) && (!fileName.trim().isEmpty())) {
            Optional<Company> company = accountDaoImpl.getCompanyByContractId(contractId);
            if (company.isPresent()) {
                String pathToAccounts = company.get().getPathToAccounts();
                if (!(pathToAccounts.trim().isEmpty())) {
                    String pathName = pathToAccounts + "\\" + fileName + ".pdf";
                    if (new File(pathName).exists()) {
                        return Optional.empty();
                    }
                }
            }
            return Optional.of(new Error("fileName", "Must be exist"));
        }
        return Optional.empty();
    }

}

