package com.zavalin.service;

import com.zavalin.domain.AccessTokenInfo;
import com.zavalin.domain.Client;
import com.zavalin.domain.User;

public interface AccessTokenService {
    AccessTokenInfo create(String accessToken, String refreshToken, int expiresIn, User user, Client client);
    AccessTokenInfo retrieve(Client client, User user);
    AccessTokenInfo save(AccessTokenInfo accessTokenInfo);
    AccessTokenInfo getForRefresh(String refreshToken, Client client);
    AccessTokenInfo getAccessTokenInfo(String accessToken);
}
