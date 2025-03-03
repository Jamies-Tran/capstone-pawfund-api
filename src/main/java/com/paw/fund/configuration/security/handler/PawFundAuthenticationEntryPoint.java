//package com.paw.fund.configuration.secure.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import java.io.IOException;
//import java.io.OutputStream;
//
//@Configuration
//public class PawFundAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        HttpStatus unauthorized = HttpStatus.FORBIDDEN;
//        ValueResponse<?> errorResponse = ValueResponse
//                .error("Không có quyền truy cập.",
//                        HttpStatus.FORBIDDEN,
//                        EErrorCode.NO_AUTHORITY.getCode(),
//                        SystemInformation.API_VERSION);
//        response.setContentType("application/json");
//        response.setStatus(unauthorized.value());
//        OutputStream os = response.getOutputStream();
//        objectMapper.writeValue(os, errorResponse);
//
//        os.flush();
//    }
//}
