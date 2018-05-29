package lv.javaguru.java2.dao;

import lv.javaguru.java2.domain.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyDao {
    void addCompany(Company company);

    void updateCompany(Company company);

    void removeCompany(Integer id);

    Optional<Company> getCompanyById(Integer id);

    Optional<Company> getCompanyByRegNr(String regNr);

    List<Company> getCompanyList();

}
