package com.zavalin.web.interceptors;

import com.zavalin.domain.AccessTokenInfo;
import com.zavalin.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private AccessTokenService accessTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("Authorization");
        if (auth != null && auth.toLowerCase().startsWith("bearer ")) {
            String token = auth.substring(7);
            AccessTokenInfo accessTokenInfo = accessTokenService.getAccessTokenInfo(token);
            if (accessTokenInfo != null) {
                Date now = new Date();
                Date expireDate = new Date();
                expireDate.setTime(accessTokenInfo.getIssueDate().getTime()
                        + accessTokenInfo.getExpiresIn() * 1000);

                if (now.before(expireDate)) {
                    return true;
                }

                response.addHeader(HttpHeaders.WWW_AUTHENTICATE, "The access token is expired");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        response.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Invalid access token");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}
