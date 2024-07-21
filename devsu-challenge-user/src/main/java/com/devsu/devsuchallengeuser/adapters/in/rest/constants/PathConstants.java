package com.devsu.devsuchallengeuser.adapters.in.rest.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PathConstants {

  public final String API = "/api/v1";

  public final String CUSTOMERS = API + "/clientes";

  public final String CUSTOMER_BY_ID = CUSTOMERS + "/{id}";
}
