package com.paw.fund.configuration.request.context;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestContext {
//    IAccountModuleFacade accountFacade;
//
//    public CurrentAccountLogin getCurrentAccountLogin() {
//        if(Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).isPresent()) {
//            String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//            return accountFacade.tryToGetDetailByEmail(email)
//                    .map(CurrentAccountLogin::of)
//                    .orElse(null);
//        }
//
//        return null;
//    }
}
