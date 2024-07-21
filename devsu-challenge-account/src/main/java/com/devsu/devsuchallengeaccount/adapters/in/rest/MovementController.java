package com.devsu.devsuchallengeaccount.adapters.in.rest;

import com.devsu.devsuchallengeaccount.adapters.in.rest.constants.PathConstants;
import com.devsu.devsuchallengeaccount.application.ports.in.MovementUseCase;
import com.devsu.devsuchallengeaccount.domain.models.MovementModel;
import com.devsu.devsuchallengeaccount.domain.service.MovementCreationFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class MovementController {

  private final MovementUseCase movementUseCase;

  private final MovementCreationFacade movementCreationFacade;

  @GetMapping(value = PathConstants.MOVEMENTS, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MovementModel>> getAllMovementByCustomer(
      @PathVariable("accountId") Integer accountId) {

    return ResponseEntity
        .ok(movementUseCase.findAllMovementsByAccount(accountId));
  }

  @GetMapping(value = PathConstants.MOVEMENT_BY_ID, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<MovementModel> getMovementById(
      @PathVariable("accountId") Integer accountId,
      @PathVariable("id") Long movementId) {

    return ResponseEntity
        .ok(movementUseCase.findMovementById(accountId, movementId));
  }

  @PostMapping(value = PathConstants.MOVEMENTS, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<MovementModel> createMovement(
      @PathVariable("accountId") Integer accountId,
      @RequestBody @Valid MovementModel movement) {

    return ResponseEntity
        .ok(movementCreationFacade.create(accountId, movement));
  }

  @PatchMapping(value = PathConstants.MOVEMENT_BY_ID, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<MovementModel> editMovement(
      @PathVariable("accountId") Integer accountId,
      @PathVariable("id") Long movementId,
      @RequestBody @Valid MovementModel movement) {

    return ResponseEntity
        .ok(movementUseCase.edit(accountId, movementId, movement));
  }

  @PutMapping(value = PathConstants.MOVEMENT_BY_ID, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<MovementModel> updateMovement(
      @PathVariable("accountId") Integer accountId,
      @PathVariable("id") Long movementId,
      @RequestBody @Valid MovementModel movement) {

    return ResponseEntity
        .ok(movementUseCase.update(accountId, movementId, movement));
  }

  @DeleteMapping(value = PathConstants.MOVEMENT_BY_ID, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deleteMovement(
      @PathVariable("accountId") Integer accountId,
      @PathVariable("id") Long movementId) {

    movementUseCase.delete(accountId, movementId);
    return ResponseEntity.noContent().build();
  }
}
