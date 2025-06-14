package com.paw.fund.common.context.auditor;

import com.paw.fund.common.context.auditor.dto.Auditor;
import com.paw.fund.utils.ObjectUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorContext {
    public static Auditor getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(ObjectUtils.isNull(auth) || !auth.isAuthenticated()) {
            return new Auditor("Anonymous");
        }

        return new Auditor((String) auth.getPrincipal());
    }
}
