package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum EErrorCode {
    AUTHORIZE_EXCEPTION("AUTH_001"),
    TOKEN_EXPIRED("AUTH_002"),
    NO_AUTHORITY("AUTH_003"),
    RESOURCE_NOT_FOUND("RESOURCE_001"),
    RESOURCE_DUPLICATED("RESOURCE_002"),
    RESOURCE_VALIDATE_FAIL("RESOURCE_003"),
    SERVER_ERROR("SERVER_001");

    String code;
}
