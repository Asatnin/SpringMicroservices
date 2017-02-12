package com.zavalin.web.model;

import com.zavalin.domain.AccessTokenInfo;

public class AccessTokenResponse {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private int expiresIn;

    public AccessTokenResponse(AccessTokenInfo accessTokenInfo) {
        this.accessToken = accessTokenInfo.getAccessToken();
        this.tokenType = accessTokenInfo.getTokenType();
        this.refreshToken = accessTokenInfo.getRefreshToken();
        this.expiresIn = accessTokenInfo.getExpiresIn();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }
}
