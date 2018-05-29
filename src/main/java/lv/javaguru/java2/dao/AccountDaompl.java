package lv.javaguru.java2.dao;

import lv.javaguru.java2.domain.Account;
import lv.javaguru.java2.domain.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountDaompl implements AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addAccount(Account account) {
        session().save(account);
    }

    @Override
    public void updateAccount(Account account) {
        session().clear();
        session().update(account);
    }

    @Override
    public void removeAccount(Long id) {
        Account deletedAccount = (Account) session().get(Account.class, id);
        if (deletedAccount != null) {
            session().delete(deletedAccount);
        }
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        Account account = (Account) session().createCriteria(Account.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(account);
    }

    @Override
    public Optional<Company> getCompanyByContractId(Integer id) {
        String sql = "select * from COMPANIES where id = (select companyId from CONTRACTS where id = :id)";
        Company company = (Company) session().createSQLQuery(sql)
                .addEntity(Company.class)
                .setParameter("id", id)
                .uniqueResult();
        return Optional.ofNullable(company);
    }

    @Override
    public List<Account> getAccountList() {
        return session()
                .createCriteria(Account.class)
                .list();
    }

    @Override
    public List<Account> getAccountListByContractId(Integer contractId) {
        return session()
                .createCriteria(Account.class)
                .add(Restrictions.eq("contractId", contractId))
                .list();
    }
}
