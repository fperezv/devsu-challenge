package com.devsu.devsuchallengeuser.infrastructure.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException {

  private final HttpStatus httpStatus;

  public HttpException(HttpStatus httpStatus, String message) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public HttpException(HttpStatus httpStatus, String message, Throwable throwable) {
    super(message, throwable);
    this.httpStatus = httpStatus;
  }
}
