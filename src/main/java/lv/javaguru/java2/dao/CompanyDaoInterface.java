package lv.javaguru.java2.dao;

import lv.javaguru.java2.domens.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyDaoInterface {
    void addCompany(Company company);

    Optional<Company> getCompanyById(Integer id);

    Optional<Company> getCompanyByRegNr(String regNr);

    void updateCompany(Company company);

    void removeCompany(Company company);

    List<Company> getCompanyList();

    List<Company> getCompanyListByClientId(Integer clientID);
}
