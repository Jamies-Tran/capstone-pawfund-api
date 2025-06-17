package com.paw.fund.common.context.request;

import com.paw.fund.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestContext {

    public static String getCurrentAccountLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(ObjectUtils.isNull(auth) || !auth.isAuthenticated()) {
            return null;
        }
        return (String) auth.getPrincipal();
    }
}
