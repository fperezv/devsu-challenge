package com.devsu.devsuchallengeaccount.infrastructure.interceptors;

import com.devsu.devsuchallengeaccount.infrastructure.exceptions.HttpException;
import com.devsu.devsuchallengeaccount.infrastructure.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static java.util.Objects.requireNonNullElse;

@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {

    final String errorDetails =
        exception
            .getBindingResult()
            .getFieldErrors()
            .stream()
            .map(fieldError ->
                fieldError.getField()
                    .concat(" -> ")
                    .concat(requireNonNullElse(fieldError.getDefaultMessage(), "error"))
                    .concat(". Current value -> ")
                    .concat("[")
                    .concat(String.valueOf(fieldError.getRejectedValue()))
                    .concat("]"))
            .collect(Collectors.joining(" | "));

    return new ResponseEntity<>(
        ErrorResponse.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .message(errorDetails)
            .build(),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception exception) {

    return new ResponseEntity<>(
        ErrorResponse.builder()
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(exception.getMessage())
            .build(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(HttpException.class)
  public ResponseEntity<ErrorResponse> handleHttpException(HttpException httpException) {

    return new ResponseEntity<>(
        ErrorResponse.builder()
            .code(httpException.getHttpStatus().value())
            .message(httpException.getMessage())
            .build(),
        httpException.getHttpStatus());
  }
}
