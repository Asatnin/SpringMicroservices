package com.zavalin.web.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RestTemplate restTemplate;

    private static final String tokenValidationUrl = "http://localhost:8080/token/valid";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpHeaders headers = new HttpHeaders();

        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                headers.add(headerName, request.getHeader(headerName));
            }
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Boolean> responseEntity;
        try {
            responseEntity = restTemplate.exchange(tokenValidationUrl, HttpMethod.GET, entity, Boolean.class);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        if (!responseEntity.getBody()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return responseEntity.getBody();
    }
}
