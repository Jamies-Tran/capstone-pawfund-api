package com.paw.fund.utils.password.encoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PawFundPasswordEncoder {
    @Bean
    public PasswordEncoder bCryptpasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
