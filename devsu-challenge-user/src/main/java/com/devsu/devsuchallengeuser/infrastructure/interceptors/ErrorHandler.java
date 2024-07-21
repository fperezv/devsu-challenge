package com.devsu.devsuchallengeuser.infrastructure.interceptors;

import com.devsu.devsuchallengeuser.infrastructure.exceptions.HttpException;
import com.devsu.devsuchallengeuser.infrastructure.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

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
