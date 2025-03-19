package com.paw.fund.app.modules.session_management.domain.usecase;

import lombok.Builder;

@Builder
public record LoginInfo(
        String email,
        String password
) {
}
