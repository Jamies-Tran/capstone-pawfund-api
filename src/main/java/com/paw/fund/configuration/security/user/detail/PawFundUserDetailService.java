package com.paw.fund.configuration.security.user.detail;


import com.paw.fund.app.modules.account.domain.Account;
import com.paw.fund.app.modules.account.domain.usecase.AccountEmail;
import com.paw.fund.app.modules.account.service.usecase.IAccountUseCase;
import com.paw.fund.configuration.handler.exceptions.AuthenticationException;
import com.paw.fund.utils.password.encoder.PawFundPasswordEncoder;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PawFundUserDetailService implements UserDetailsService {
    @NonNull
    IAccountUseCase accountUseCase;

    @NonNull
    PawFundPasswordEncoder appPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountUseCase.getAccountForAuth(AccountEmail.of(username));

        return Optional.of(account)
                .map(x -> User.builder()
                        .username(x.email())
                        .password(appPasswordEncoder.bCryptpasswordEncoder().encode(x.password()))
                        .disabled(false)
                        .authorities(List.of(new SimpleGrantedAuthority("ROLE_%s".formatted(x.roleCode()))))
                        .build())
                .orElseThrow(AuthenticationException::new);
    }
}
