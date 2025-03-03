package com.paw.fund.configuration.handler;

import com.paw.fund.configuration.handler.exceptions.AuthenticationException;
import com.paw.fund.configuration.handler.exceptions.ResourceDuplicateException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.configuration.handler.exceptions.TokenExpiredException;
import com.paw.fund.enums.EErrorCode;
import com.paw.fund.utils.response.ValueResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GlobalExceptionHandler {
    @Value("${app.version}")
    String API_VERSION;

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ValueResponse<?> authenticationExceptionHandler(AuthenticationException exc) {
        return ValueResponse.error(
                exc.getMessage(),
                HttpStatus.UNAUTHORIZED,
                EErrorCode.AUTHORIZE_EXCEPTION.getCode(),
                API_VERSION);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenExpiredException.class)
    public ValueResponse<?> tokenExpiredExceptionHandler(TokenExpiredException exc) {
        return ValueResponse.error(
                exc.getMessage(),
                HttpStatus.UNAUTHORIZED,
                EErrorCode.TOKEN_EXPIRED.getCode(),
                API_VERSION);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ValueResponse<?> resourceNotFoundExceptionHandler(ResourceNotFoundException exc) {
        return ValueResponse.error(
                exc.getMessage(),
                HttpStatus.NOT_FOUND,
                EErrorCode.RESOURCE_NOT_FOUND.getCode(),
                API_VERSION);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceDuplicateException.class)
    public ValueResponse<?> resourceDuplicatedExceptionHandler(ResourceDuplicateException exc) {
        return ValueResponse.error(
                exc.getMessage(),
                HttpStatus.CONFLICT,
                EErrorCode.RESOURCE_DUPLICATED.getCode(),
                API_VERSION);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ValueResponse<?> internalExceptionHandler(Exception exc) {
        return ValueResponse.error(
                exc.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                EErrorCode.SERVER_ERROR.getCode(),
                API_VERSION);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValueResponse<?> methodArgumentExceptionHandler(MethodArgumentNotValidException exc) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError fieldError : exc.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ValueResponse.error(
                errors,
                HttpStatus.BAD_REQUEST,
                EErrorCode.RESOURCE_VALIDATE_FAIL.getCode(),
                API_VERSION);
    }
}
