package com.macons.apparkme.controller;

import com.macons.apparkme.dto.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/apparkme/api/v1")
@RestController
public class LoginController {

    @Value("${jwt.token.secret}")
    private String secret;

    @GetMapping("hola")
    public String getHola(){
        return "HOLA";
    }

    @PostMapping(value = "login")
    public UserDTO login(@RequestParam("username") String username, @RequestParam("password") String pwd) {
        log.info("\n\n Logueando usuario..... \n\n", username);
        String token = getJWTToken(username);
        UserDTO user = new UserDTO();
        user.setUser(username);
        user.setToken(token);
        log.info("\n\n El usuario {} se ha logueado con exito.... \n\n", username);
        return user;

    }

    private String getJWTToken(String username) {

        log.info("{}", generateSafeToken());

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(this.secret);
        Key signingKey = Keys.hmacShaKeyFor(apiKeySecretBytes);

        log.info("\n\n Creando token.... \n\n");
        String secretKey = "mySecretKey";
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
                .signWith(signingKey).compact();

        return "Bearer " + token;
    }

    private String generateSafeToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[36]; // 36 bytes * 8 = 288 bits, a little bit more than
        // the 256 required bits
        random.nextBytes(bytes);
        var encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(bytes);
    }
}
