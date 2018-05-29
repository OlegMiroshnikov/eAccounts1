package lv.javaguru.java2.dao;

import lv.javaguru.java2.domain.Client;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.Contract;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ContractDaoImpl implements ContractDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addContract(Contract contract) {
        session().save(contract);
    }

    @Override
    public void updateContract(Contract contract) {
        session().clear();
        session().update(contract);
    }

    @Override
    public void removeContract(Integer id) {
        Contract deletedContract = (Contract) session().get(Contract.class, id);
        if (deletedContract != null) {
            session().delete(deletedContract);
        }
    }

    @Override
    public Optional<Contract> getContractById(Integer id) {
        Contract contract = (Contract) session().createCriteria(Contract.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(contract);
    }

    @Override
    public Optional<Contract> getContractByCompanyIdAndClientId(Integer companyId, Integer clientId) {
        Contract contract = (Contract) session().createCriteria(Contract.class)
                .add(Restrictions.eq("companyId", companyId))
                .add(Restrictions.eq("clientId", clientId))
                .uniqueResult();
        return Optional.ofNullable(contract);
    }

    @Override
    public List<Contract> getContractList() {
        return session()
                .createCriteria(Contract.class)
                .list();
    }

    @Override
    public List<Contract> getContractListByClientId(Integer clientId) {
        return session()
                .createCriteria(Contract.class)
                .add(Restrictions.eq("clientId", clientId))
                .list();
    }

    @Override
    public List<Contract> getContractListByCompanyId(Integer companyId) {
        return session()
                .createCriteria(Contract.class)
                .add(Restrictions.eq("companyId", companyId))
                .list();
    }

    @Override
    public List<Company> getCompanyListByClientId(Integer clientId) {
        List<Contract> contracts = session()
                .createCriteria(Contract.class)
                .add(Restrictions.eq("clientId", clientId))
                .list();
        List<Company> companies = new ArrayList<>();
        for (Contract contract : contracts) {
            companies.add(contract.getCompany());
        }
        return companies;
    }

    @Override
    public List<Client> getClientListByCompanyId(Integer companyId) {
        List<Contract> contracts = session()
                .createCriteria(Contract.class)
                .add(Restrictions.eq("companyId", companyId))
                .list();
        List<Client> clients = new ArrayList<>();
        for (Contract contract : contracts) {
            clients.add(contract.getClient());
        }
        return clients;
    }
}
