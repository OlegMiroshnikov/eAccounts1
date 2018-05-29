package lv.javaguru.java2.businesslogic.contract.addcontract;

import lv.javaguru.java2.dao.ClientDao;
import lv.javaguru.java2.dao.CompanyDao;
import lv.javaguru.java2.dao.ContractDao;
import lv.javaguru.java2.domain.Client;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.Contract;
import lv.javaguru.java2.validators.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class AddContractValidator {

    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private CompanyDao companyDao;

    public List<Error> validate(AddContractRequest request) {
        List<Error> errors = new ArrayList<>();
        validateCompanyID(request.getCompanyId()).ifPresent(errors::add);
        validateClientID(request.getClientId()).ifPresent(errors::add);
        validateNumber(request.getNumber()).ifPresent(errors::add);
        validateDateSign(request.getDateSign()).ifPresent(errors::add);
        validateDateBegin(request.getDateBegin()).ifPresent(errors::add);
        validateDublicateContractBetweenCompanyAndClient(request.getCompanyId(), request.getClientId()).ifPresent(errors::add);
        validateClientByIdIsExist(request.getClientId()).ifPresent(errors::add);
        validateCompanyByIdIsExist(request.getCompanyId()).ifPresent(errors::add);
        return errors;
    }

    private Optional<Error> validateCompanyID(Integer companyID) {
        if (companyID == null) {
            return Optional.of(new Error("companyId", "Must not be null"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateClientID(Integer clientID) {
        if (clientID == null) {
            return Optional.of(new Error("clientId", "Must not be null"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateNumber(String number) {
        if (number == null || number.trim().isEmpty()) {
            return Optional.of(new Error("number", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateDateSign(Date dateSign) {
        if (dateSign == null) {
            return Optional.of(new Error("dateSign", "Must not be null"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateDateBegin(Date dateBegin) {
        if (dateBegin == null) {
            return Optional.of(new Error("dateBegin", "Must not be null"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> validateDublicateContractBetweenCompanyAndClient(Integer companyId, Integer clientId) {
        if (companyId != null && clientId != null) {
            Optional<Contract> contract = contractDao.getContractByCompanyIdAndClientId(companyId, clientId);
            if (contract.isPresent()) {
                return Optional.of(new Error("", "The contract between the company and the client already exists"));
            }
        }
        return Optional.empty();
    }

    private Optional<Error> validateClientByIdIsExist(Integer clientId) {
        if (clientId != null) {
            Optional<Client> client = clientDao.getClientById(clientId);
            if (!client.isPresent()) {
                return Optional.of(new Error("contract", "Client must be exist"));
            }
        }
        return Optional.empty();
    }

    private Optional<Error> validateCompanyByIdIsExist(Integer companyId) {
        if (companyId != null) {
            Optional<Company> company = companyDao.getCompanyById(companyId);
            if (!company.isPresent()) {
                return Optional.of(new Error("contract", "Company must be exist"));
            }
        }
        return Optional.empty();
    }

}