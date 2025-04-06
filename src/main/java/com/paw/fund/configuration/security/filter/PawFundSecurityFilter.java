package com.paw.fund.configuration.security.filter;


import com.paw.fund.configuration.handler.exceptions.AuthenticationException;
import com.paw.fund.configuration.security.user.detail.PawFundUserDetailService;
import com.paw.fund.enums.EErrorCode;
import com.paw.fund.utils.mapper.AppObjectMapper;
import com.paw.fund.utils.response.ValueResponse;
import com.paw.fund.utils.token.TokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PawFundSecurityFilter extends OncePerRequestFilter {
    TokenUtil tokenUtil;

    PawFundUserDetailService userDetailsService;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

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
                            EErrorCode.TOKEN_EXPIRED.getCode(),
                            API_VERSION);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write(AppObjectMapper.convertDataToJsonString(errorResponse));
        } catch (AuthenticationException e) {
            ValueResponse<?> errorResponse = ValueResponse
                    .error(e.getMessage(),
                            HttpStatus.UNAUTHORIZED,
                            EErrorCode.NO_AUTHORITY.getCode(),
                            API_VERSION);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json");
            response.getWriter().write(AppObjectMapper.convertDataToJsonString(errorResponse));
        } catch (RuntimeException e) {
            log.error("[{}-doFilterInternal] Xác thực that bại", this.getClass().getSimpleName());
            ValueResponse<?> errorResponse = ValueResponse
                    .error("Lỗi xác thực",
                            HttpStatus.UNAUTHORIZED,
                            EErrorCode.AUTHORIZE_EXCEPTION.getCode(),
                            API_VERSION);
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
}
