package com.devsu.devsuchallengeuser.infrastructure.utils;

import java.util.List;

public interface ModelMapper<E, M> {

  E mapOut(M model);

  M mapIn(E entity);

  default List<E> mapOut(List<M> models) {
    return models
        .stream()
        .map(this::mapOut)
        .toList();
  }

  default List<M> mapIn(List<E> entities) {
    return entities
        .stream()
        .map(this::mapIn)
        .toList();
  }
}
