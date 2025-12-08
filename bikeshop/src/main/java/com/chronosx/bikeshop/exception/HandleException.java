package com.chronosx.bikeshop.exception;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chronosx.bikeshop.dto.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class HandleException {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<?>> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        String message = e.getPlaceHolder() != null
                ? String.format(errorCode.getMessage(), e.getPlaceHolder())
                : errorCode.getMessage();

        return errorResponse(errorCode, message);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiResponse<?>> handleIOException(IOException e) {
        return errorResponse(ErrorCode.IO_ERROR, e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleJsonParseError(HttpMessageNotReadableException e) {
        ErrorCode errorCode = ErrorCode.JSON_PARSE_ERROR;
        log.error("JSON parse error: {}", e.getMessage());

        return errorResponse(errorCode, errorCode.getMessage());
    }

    private ResponseEntity<ApiResponse<?>> errorResponse(ErrorCode errorCode, String message) {
        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(message)
                        .build());
    }
}
