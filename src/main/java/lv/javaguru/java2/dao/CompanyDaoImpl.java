package lv.javaguru.java2.dao;

import lv.javaguru.java2.domain.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CompanyDaoImpl implements CompanyDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addCompany(Company company) {
        session().save(company);
    }

    @Override
    public void updateCompany(Company company) {
        session().clear();
        session().update(company);
    }

    @Override
    public void removeCompany(Integer id) {
        Company deletedCompany = (Company) session().get(Company.class, id);
        if (deletedCompany != null) {
            session().delete(deletedCompany);
        }
    }

    @Override
    public Optional<Company> getCompanyById(Integer id) {
        Company company = (Company) session().createCriteria(Company.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(company);
    }

    @Override
    public Optional<Company> getCompanyByRegNr(String regNr) {
        Company company = (Company) session().createCriteria(Company.class)
                .add(Restrictions.eq("regNr", regNr))
                .uniqueResult();
        return Optional.ofNullable(company);

    }

    @Override
    public List<Company> getCompanyList() {
        return session()
                .createCriteria(Company.class)
                .list();
    }

}
