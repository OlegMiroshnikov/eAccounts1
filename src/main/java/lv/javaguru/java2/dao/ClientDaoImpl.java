package lv.javaguru.java2.dao;

import lv.javaguru.java2.domain.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public void addClient(Client client) {
//        try {
//            session().save(client);
//        } catch (Throwable e) {
//            System.out.println("Exception while execute ClientDaoImpl.addClient(): " + e.getClass());
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } finally {
//            System.out.println(client);
//        }
        session().save(client);
    }

    @Override
    public void updateClient(Client client) {
        session().clear();
        session().update(client);
    }

    @Override
    public void removeClient(Integer id) {
//        session().delete(client);
        Client deletedClient = (Client) session().get(Client.class, id);
        if (deletedClient != null) {
            session().delete(deletedClient);
        }
    }

    @Override
    public Optional<Client> getClientById(Integer id) {
        Client client = (Client) session().createCriteria(Client.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(client);
//        Client client = (Client) session().get(Client.class, id);
//        return Optional.ofNullable(client);

    }

    @Override
    public Optional<Client> getClientByPersonalCode(String personalCode) {
        Client client = (Client) session().createCriteria(Client.class)
                .add(Restrictions.eq("personalCode", personalCode))
                .uniqueResult();
        return Optional.ofNullable(client);
    }

    @Override
    public List<Client> getClientList() {
        return session()
                .createCriteria(Client.class)
                .list();
    }
}
