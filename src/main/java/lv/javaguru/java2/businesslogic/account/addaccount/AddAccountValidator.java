package lv.javaguru.java2.businesslogic.account.addaccount;

import lv.javaguru.java2.dao.AccountDao;
import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.Contract;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddAccountValidator {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ContractDao contractDao;

    public List<Error> validate(AddAccountRequest request) {
        List<Error> errors = new ArrayList<>();
        validateContractID(request.getContractId()).ifPresent(errors::add);
        validateContractByIdIsExist(request.getContractId()).ifPresent(errors::add);
        validateFileName(request.getFileName()).ifPresent(errors::add);
        validateFileExist(request.getFileName(), request.getContractId()).ifPresent(errors::add);
        return errors;
    }

    private Optional<Error> validateContractID(Integer contractID) {
        if (contractID == null) {
            return Optional.of(new Error("contractId", "Must not be null"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateContractByIdIsExist(Integer contractId) {
        if (contractId != null) {
            Optional<Contract> contract = contractDao.getContractById(contractId);
            if (!contract.isPresent()) {
                return Optional.of(new Error("account", "Contract must be exist"));
            }
        }
        return Optional.empty();
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
//            Optional<Contract> contract = contractDao.getContractById(contractId);
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
            Optional<Company> company = accountDao.getCompanyByContractId(contractId);
            String pathName = "";
            if (company.isPresent()) {
                String pathToAccounts = company.get().getPathToAccounts();
                if (!(pathToAccounts.trim().isEmpty())) {
                    pathName = pathToAccounts + "\\" + fileName + ".pdf";
                    if (new File(pathName).exists()) {
                        return Optional.empty();
                    }
                }
            }
            return Optional.of(new Error("fileName", "Must be exist"));
//            return Optional.of(new Error(pathName, "Must be exist"));
        }
        return Optional.empty();
    }

}

