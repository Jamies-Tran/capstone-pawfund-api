package com.paw.fund.configuration.security.filter;


import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.Login;
import com.paw.fund.app.modules.login_info_management.repository.database.ILoginInfoUseCase;
import com.paw.fund.configuration.handler.exceptions.AuthenticationException;
import com.paw.fund.configuration.handler.exceptions.LoginException;
import com.paw.fund.configuration.security.user.detail.PawFundUserDetailService;
import com.paw.fund.enums.EErrorCode;
import com.paw.fund.enums.ELoginStatus;
import com.paw.fund.utils.ObjectUtils;
import com.paw.fund.utils.mapper.AppObjectMapper;
import com.paw.fund.utils.response.ValueResponse;
import com.paw.fund.utils.token.TokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PawFundSecurityFilter extends OncePerRequestFilter {
    @NonNull
    TokenUtil tokenUtil;

    @NonNull
    PawFundUserDetailService userDetailsService;

    @NonNull
    ILoginInfoUseCase loginInfoUseCase;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        try {

            String token = tokenUtil.getTokenFromRequest(request);
            if(Optional.ofNullable(token).isPresent()) {
                tokenUtil.validateToken(token);
                String identification = tokenUtil.getUserIdentifyFromToken(token);

                validateLogin(identification);

                UserDetails userDetails = userDetailsService.loadUserByUsername(identification);
                UsernamePasswordAuthenticationToken authToken = UsernamePasswordAuthenticationToken.authenticated(
                        userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authToken);
                SecurityContextHolder.setContext(securityContext);
                log.info("Authenticated with token: {}", token);
            }

            filterChain.doFilter(requestWrapper , response);

        } catch (ExpiredJwtException e) {
            log.error("[{}-doFilterInternal] Token không hợp lệ", this.getClass().getSimpleName());
            ValueResponse<?> errorResponse = ValueResponse
                    .error("Phiên đăng nhập đã hết hạn.",
                            HttpStatus.UNAUTHORIZED,
                            EErrorCode.TOKEN_EXPIRED.getCode());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write(AppObjectMapper.convertDataToJsonString(errorResponse));
        } catch (AuthenticationException e) {
            ValueResponse<?> errorResponse = ValueResponse
                    .error(e.getMessage(),
                            HttpStatus.UNAUTHORIZED,
                            EErrorCode.NO_AUTHORITY.getCode());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json");
            response.getWriter().write(AppObjectMapper.convertDataToJsonString(errorResponse));
        } catch (LoginException exc) {
            log.error("[{}-doFilterInternal] Xác thực that bại", this.getClass().getSimpleName());
            ValueResponse<?> errorResponse = ValueResponse
                    .error(exc.getMessage(), HttpStatus.BAD_REQUEST, exc.getCode());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write(AppObjectMapper.convertDataToJsonString(errorResponse));
        } catch (RuntimeException e) {
            log.error("[{}-doFilterInternal] Xác thực that bại", this.getClass().getSimpleName());
            ValueResponse<?> errorResponse = ValueResponse
                    .error("Lỗi xác thực",
                            HttpStatus.UNAUTHORIZED,
                            EErrorCode.AUTHORIZE_EXCEPTION.getCode());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write(AppObjectMapper.convertDataToJsonString(errorResponse));
        } finally {
            String authorization = requestWrapper.getHeader("Authorization");
            String method = request.getMethod();
            String path = request.getRequestURI();
            String body = new String(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
            List<String> paramList = requestWrapper.getParameterMap().entrySet().stream()
                    .map(x -> "%s - %s".formatted(x.getKey(), String.join(", ", x.getValue()))).toList();
            String param = String.join(",", paramList);
            String requestAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            log.info("\n{\n Request logging: " +
                    "\n Authorization: [{}]" +
                    "\n Method: [{}]  " +
                    "\n Path: [{}]" +
                    "\n Param: [{}]" +
                    "\n Body: [{}]" +
                    "\n time: [{}]\n}", authorization, method, path, param, body, requestAt);
        }
    }

    private void validateLogin(String identification) {
        Login login = loginInfoUseCase.getCurrentLoginInfo(identification);

        if (!Objects.equals(login.statusCode(), ELoginStatus.LOGIN.getCode())) {
            throw new LoginException(EErrorCode.LOGIN_NOT_VALID.getCode(), "Xin vui lòng đăng nhập lại");
        }

        if (ObjectUtils.isNotNull(login.accessExpiredAt())
                && login.accessExpiredAt().isBefore(LocalDateTime.now())) {
            throw new LoginException(EErrorCode.LOGIN_NOT_VALID.getCode(), "Xin vui lòng đăng nhập lại");
        }

        if (ObjectUtils.isNotNull(login.refreshExpiredAt())
                && login.refreshExpiredAt().isBefore(LocalDateTime.now())) {
            throw new LoginException(EErrorCode.LOGIN_EXPIRED.getCode(), "");
        }

    }
}
