package com.devsu.devsuchallengeaccount.domain;

import com.devsu.devsuchallengeaccount.infrastructure.exceptions.HttpException;
import lombok.experimental.UtilityClass;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@UtilityClass
public class Exceptions {

  public static final HttpException ACCOUNT_NOT_FOUND =
      new HttpException(NOT_FOUND, "Cuenta no encontrada");

  public static final HttpException ACCOUNT_ALREADY_EXISTS_NUMBER =
      new HttpException(CONFLICT, "El número de cuenta ya existe");

  public static final HttpException ACCOUNT_BALANCE_UNAVAILABLE =
      new HttpException(CONFLICT, "Saldo no disponible");

  public static final HttpException ACCOUNT_TYPE_NOT_VALID =
      new HttpException(BAD_REQUEST, "Tipo de cuenta no valido");

  public static final HttpException MOVEMENT_NOT_FOUND =
      new HttpException(NOT_FOUND, "Movimiento no encontrado");

  public static final HttpException MISSED_METHOD_INVOKED =
      new HttpException(INTERNAL_SERVER_ERROR, "Missed method invoked");
}
