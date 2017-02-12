package com.zavalin.service;

import com.zavalin.domain.AccessTokenInfo;
import com.zavalin.domain.Client;
import com.zavalin.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;

@Repository
@Transactional
public class AccessTokenImpl implements AccessTokenService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AccessTokenInfo create(String accessToken, String refreshToken, int expiresIn,
                                  User user, Client client) {
        AccessTokenInfo accessTokenInfo = new AccessTokenInfo();
        accessTokenInfo.setAccessToken(accessToken);
        accessTokenInfo.setRefreshToken(refreshToken);
        accessTokenInfo.setTokenType("bearer");
        accessTokenInfo.setExpiresIn(expiresIn);
        accessTokenInfo.setIssueDate(new Date());
        accessTokenInfo.setUser(user);
        accessTokenInfo.setClient(client);

        entityManager.persist(accessTokenInfo);
        return accessTokenInfo;
    }

    @Override
    public AccessTokenInfo retrieve(Client client, User user) {
        TypedQuery<AccessTokenInfo> query = entityManager
                .createQuery("from AccessTokenInfo where client = :client and user = :user",
                        AccessTokenInfo.class);
        query.setParameter("client", client);
        query.setParameter("user", user);
        return query.getResultList().size() == 0 ? null : query.getResultList().get(0);
    }

    @Override
    public AccessTokenInfo save(AccessTokenInfo accessTokenInfo) {
        AccessTokenInfo oldInfo = entityManager.find(AccessTokenInfo.class,
                accessTokenInfo.getId());
        oldInfo.setAccessToken(accessTokenInfo.getAccessToken());
        oldInfo.setRefreshToken(accessTokenInfo.getRefreshToken());
        oldInfo.setExpiresIn(accessTokenInfo.getExpiresIn());
        oldInfo.setIssueDate(new Date());

        entityManager.persist(oldInfo);
        return oldInfo;
    }

    @Override
    public AccessTokenInfo getForRefresh(String refreshToken, Client client) {
        TypedQuery<AccessTokenInfo> query = entityManager
                .createQuery("from AccessTokenInfo where refreshtoken = :refreshtoken "
                                + "and client = :client", AccessTokenInfo.class);
        query.setParameter("refreshtoken", refreshToken);
        query.setParameter("client", client);
        return query.getResultList().size() == 0 ? null : query.getResultList().get(0);
    }

    @Override
    public AccessTokenInfo getAccessTokenInfo(String accessToken) {
        TypedQuery<AccessTokenInfo> query = entityManager
                .createQuery("from AccessTokenInfo where accesstoken = :accesstoken",
                        AccessTokenInfo.class);
        query.setParameter("accesstoken", accessToken);
        return query.getResultList().size() == 0 ? null : query.getResultList().get(0);
    }
}
