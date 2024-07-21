package com.devsu.devsuchallengeaccount.adapters.in.rest.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PathConstants {

  public final String API = "/api/v1";

  public final String ACCOUNTS = API + "/{customerId}/cuentas";

  public final String MOVEMENTS = API + "/{accountId}/movimientos";

  public final String ACCOUNT_BY_ID = ACCOUNTS + "/{id}";

  public final String MOVEMENT_BY_ID = MOVEMENTS + "/{id}";

  public final String REPORTS = API + "/reportes";
}
