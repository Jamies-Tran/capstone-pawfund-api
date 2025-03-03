//package com.paw.fund.configuration.secure.filter;
//
//
//import io.jsonwebtoken.ExpiredJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Objects;
//import java.util.Optional;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class PawFundSecurityFilter extends OncePerRequestFilter {
//    JwtUtils jwtUtils;
//
//    PawFundUserDetailService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        try {
//            String token = jwtUtils.getTokenFromRequest(request);
//            if(Optional.ofNullable(token).isPresent()) {
//                jwtUtils.validateToken(token);
//                String identification = jwtUtils.getUserIdentifyFromToken(token);
//                UserDetails userDetails = userDetailsService.loadUserByUsername(identification);
//                UsernamePasswordAuthenticationToken authToken = UsernamePasswordAuthenticationToken.authenticated(
//                        userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
//                SecurityContext securityContext = SecurityContextHolder.getContext();
//                securityContext.setAuthentication(authToken);
//                SecurityContextHolder.setContext(securityContext);
//                log.info("Authenticated with token: {}", token);
//            }
//
//            filterChain.doFilter(request, response);
//
//        } catch (ExpiredJwtException e) {
//            log.error("[{}-doFilterInternal] Token không hợp lệ", this.getClass().getSimpleName());
//            ValueResponse<?> errorResponse = ValueResponse
//                    .error("Phiên đăng nhập đã hết hạn.",
//                            HttpStatus.UNAUTHORIZED,
//                            EErrorCode.TOKEN_EXPIRED.getCode(),
//                            SystemInformation.API_VERSION);
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.setContentType("application/json");
//            response.getWriter().write(AppObjectMapper.convertDataToJsonString(errorResponse));
//        } catch (AuthenticationException e) {
//            ValueResponse<?> errorResponse = ValueResponse
//                    .error(e.getMessage(),
//                            HttpStatus.UNAUTHORIZED,
//                            EErrorCode.NO_AUTHORITY.getCode(),
//                            SystemInformation.API_VERSION);
//            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            response.setContentType("application/json");
//            response.getWriter().write(AppObjectMapper.convertDataToJsonString(errorResponse));
//        } catch (RuntimeException e) {
//            log.error("[{}-doFilterInternal] Xác thực that bại", this.getClass().getSimpleName());
//            ValueResponse<?> errorResponse = ValueResponse
//                    .error("Có lỗi xảy ra.",
//                            HttpStatus.INTERNAL_SERVER_ERROR,
//                            EErrorCode.SERVER_ERROR.getCode(),
//                            SystemInformation.API_VERSION);
//            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            response.setContentType("application/json");
//            response.getWriter().write(AppObjectMapper.convertDataToJsonString(errorResponse));
//        }
//    }
//}
