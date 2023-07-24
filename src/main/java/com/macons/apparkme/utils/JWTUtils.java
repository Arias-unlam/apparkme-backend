package com.macons.apparkme.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;

public class JWTUtils {

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    private JWTUtils() {
        throw new IllegalStateException("Utility class");
    }
    public static boolean existeJWTToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
            return false;
        return true;
    }

    public static Claims validateToken(HttpServletRequest request, String secret) {
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwtToken).getBody();
    }
}
