package com.zavalin.service;

import com.zavalin.domain.Client;
import com.zavalin.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class ClientServiceImpl implements ClientService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean isExists(String clientId) {
        TypedQuery<Client> query = entityManager
                .createQuery("from Client where client_id = :client_id", Client.class);
        query.setParameter("client_id", clientId);

        return query.getResultList().size() > 0;
    }

    @Override
    public Client updateClientCode(String clientId, String code) {
        TypedQuery<Client> query = entityManager
                .createQuery("from Client where client_id = :client_id", Client.class);
        query.setParameter("client_id", clientId);

        Client client = query.getSingleResult();
        client.setCode(code);

        entityManager.persist(client);
        return client;
    }

    @Override
    public Client updateClientRedirectUri(String clientId, String redirectUri) {
        TypedQuery<Client> query = entityManager
                .createQuery("from Client where client_id = :client_id", Client.class);
        query.setParameter("client_id", clientId);

        Client client = query.getSingleResult();
        client.setRedirectUri(redirectUri);

        entityManager.persist(client);
        return client;
    }

    @Override
    public Client getClient(String clientId, String clientSecret, String code) {
        TypedQuery<Client> query = entityManager
                .createQuery("from Client where client_id = :client_id "
                        + "and client_secret = :client_secret "
                        + "and code = :code", Client.class);
        query.setParameter("client_id", clientId);
        query.setParameter("client_secret", clientSecret);
        query.setParameter("code", code);

        return query.getResultList().size() == 0 ? null : query.getResultList().get(0);
    }

    @Override
    public Client getClient(String clientId, String clientSecret) {
        TypedQuery<Client> query = entityManager
                .createQuery("from Client where client_id = :client_id "
                        + "and client_secret = :client_secret", Client.class);
        query.setParameter("client_id", clientId);
        query.setParameter("client_secret", clientSecret);

        return query.getResultList().size() == 0 ? null : query.getResultList().get(0);
    }

    @Override
    public Client updateClientUser(String clientId, User user) {
        TypedQuery<Client> query = entityManager
                .createQuery("from Client where client_id = :client_id", Client.class);
        query.setParameter("client_id", clientId);

        Client client = query.getSingleResult();
        client.setUser(user);

        entityManager.persist(client);
        return client;
    }
}
