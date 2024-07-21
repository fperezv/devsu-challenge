package com.devsu.devsuchallengeaccount.infrastructure.utils;

import lombok.experimental.UtilityClass;

import java.util.function.Consumer;

import static org.springframework.util.ObjectUtils.isEmpty;
import static org.springframework.util.StringUtils.hasText;

@UtilityClass
public class FunctionUtils {

  public <K> void populateIfPresent(K value, Consumer<K> setter) {

    if (isEmpty(value)) {
      return;
    }

    if (value instanceof String &&
        !hasText(value.toString())) {
      return;
    }

    setter.accept(value);
  }
}
