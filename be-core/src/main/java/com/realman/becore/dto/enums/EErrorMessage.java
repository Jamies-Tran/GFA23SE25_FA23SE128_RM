package com.realman.becore.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EErrorMessage {
    JWT_NOT_VALID("Đăng nhập không hợp lệ"),
    AUTHENTICATION_FAIL("Đăng nhập không thành công"),
    RESOURCE_NOT_FOUND("Không tìm thấy tài nguyên"),
    RESOURCE_INVALID("Tài nguyên không hợp lệ"),
    RESOURCE_DUPLICATED("Tài nguyên đã tồn tại");

    private final String message;
}
