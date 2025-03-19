package com.paw.fund.configuration.handler;

import com.paw.fund.configuration.handler.exceptions.RuntimeException;
import com.paw.fund.configuration.handler.exceptions.AuthenticationException;
import com.paw.fund.configuration.handler.exceptions.IllegalArgumentException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.configuration.handler.exceptions.ServiceException;
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
import com.paw.fund.configuration.handler.exceptions.NullPointerException;

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
    @ExceptionHandler(RuntimeException.class)
    public ValueResponse<?> internalExceptionHandler(RuntimeException exc) {
        return ValueResponse.error(
                exc.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                EErrorCode.SERVER_ERROR.getCode(),
                API_VERSION);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ValueResponse<?> nullPointerExceptionHandler(NullPointerException exc) {
        return ValueResponse.error(
                exc.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                EErrorCode.NULL_ERROR.getCode(),
                API_VERSION
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    public ValueResponse<?> illegalArgumentExceptionHandler(IllegalArgumentException exc) {
        return ValueResponse.error(
                exc.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                EErrorCode.ARGUMENT_ERROR.getCode(),
                API_VERSION
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public ValueResponse<?> operationNotSupportedExceptionHandler(ServiceException exc) {
        return ValueResponse.error(
                exc.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                EErrorCode.SERVICE_ERROR.getCode(),
                API_VERSION
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceNotValidException.class)
    public ValueResponse<?> resourceNotValidExceptionHandler(ResourceNotValidException exc) {
        return ValueResponse.error(
                exc.getMessage(),
                HttpStatus.BAD_REQUEST,
                EErrorCode.SERVICE_ERROR.getCode(),
                API_VERSION
        );
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
