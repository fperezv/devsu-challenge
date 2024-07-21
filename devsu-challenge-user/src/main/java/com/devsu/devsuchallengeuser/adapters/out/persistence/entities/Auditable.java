package com.devsu.devsuchallengeuser.adapters.out.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Auditable {

  @CreatedDate
  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @LastModifiedDate
  @Column(name = "updated_date")
  private LocalDateTime updatedDate;
}
