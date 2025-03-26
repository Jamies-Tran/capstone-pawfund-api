package com.paw.fund.app.modules.account_management.controller.v1.pub;

import com.paw.fund.app.modules.account_management.controller.models.AccountRequest;
import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.verification.code.AccountVerificationCodeRequest;
import com.paw.fund.utils.response.PageResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/public/account")
@Tag(name = "Account", description = "QL tài khoản")
public interface IAccountV1PubAPI {
    @PostMapping
    @Operation(
            summary = "Tạo tài khoản",
            description = """
                    - Người dùng tạo tài khoản
                    """)
    ValueResponse<AccountResponse> createAccount(@RequestBody @Valid AccountRequest accountRequest);

    @PostMapping("/donor-adoptor")
    @Operation(
            summary = "Tạo tài khoản người nguyên góp và người nhận nuôi",
            description = """
                    - Người dùng tạo tài khoản người nguyên góp và người nhận nuôi
                    """)
    ValueResponse<AccountResponse> createDonorAndAdopterAccount(@RequestBody @Valid AccountRequest accountRequest);

    @GetMapping
    @Operation(
            summary = "Tìm kiếm danh sách tài khoản",
            description = """
                    - Người dùng tìm kiếm danh sách tài khoản
                    """)
    PageResponse<AccountResponse> findAll(
            @Schema(description = """
                    Tìm kiếm theo:
                    - họ và tên
                    - địa chỉ
                    - sđt
                    - CCCD
                    - email
                    """)
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,

            @ArraySchema(schema = @Schema(description = """
                    Tìm kiếm ngày tạo theo khoản thời gian (mặc định là 7 ngày trước)
                    """))
            @RequestParam(required = false, value = "timeRange", defaultValue = "")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> timeRange,

            @ArraySchema(schema = @Schema(description = """
                    Tìm kiếm ngày sinh của người dung theo khoản thời gian
                    """))
            @RequestParam(required = false, value = "dateOfBirth", defaultValue = "")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            List<LocalDate> dateOfBirth,

            @ArraySchema(schema = @Schema(description = """
                    Tìm kiếm theo danh sách mã trạng thái
                    """))
            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,

            @ArraySchema(schema = @Schema(description = """
                    Tìm kiếm theo danh sách mã phân quyền
                    """))
            @RequestParam(required = false, value = "roleCodes", defaultValue = "")
            List<String> roleCodes,

            @ArraySchema(schema = @Schema(description = """
                    Tìm kiếm theo danh sách mã giới tính
                    """))
            @RequestParam(required = false, value = "genderCodes", defaultValue = "")
            List<String> genderCodes,

            @Schema(description = """
                    Sắp xếp theo điều kiện (mặc định là ngày cập nhật)
                    """)
            @RequestParam(required = false, value = "sorter", defaultValue = "updatedAt")
            String sorter,

            @Schema(description = """
                    Trang hiện tại (mặc định là trang đầu tiên)
                    """)
            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @Schema(description = """
                    Phần tử trên một trang (mặc định 25 phần tử)
                    """)
            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );

    @PatchMapping("/verify")
    @Operation(
            summary = "Xác minh tài khoản",
            description = """
                    - Người dùng xác minh bang mã xác thực gửi qua mail
                    """)
    ValueResponse<AccountResponse> verifyCreatedAccount(
            @RequestBody @Valid AccountVerificationCodeRequest request);
}
