package com.macons.apparkme.service.impl;

import com.macons.apparkme.config.ApplicationProperties;
import com.macons.apparkme.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    ApplicationProperties applicationProperties;

    @Override
    public String getJWTToken(String username) {
        log.info("{}", applicationProperties.secret);
        log.info("\n\n Creando token.... \n\n");
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        log.info("\n\n Generando Jwts.... \n\n");
        String token = Jwts
                .builder()
                .setId("apparkmeJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        applicationProperties.secret.getBytes()).compact();
        return "Bearer " + token;
    }

}
