package com.macons.apparkme.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationProperties {

    @Value("${jwt.token.secret}")
    public String secret;


}
