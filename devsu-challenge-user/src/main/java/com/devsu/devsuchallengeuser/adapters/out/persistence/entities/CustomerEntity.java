package com.devsu.devsuchallengeuser.adapters.out.persistence.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerEntity {

  @Id
  private Integer id;

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "id", referencedColumnName = "id")
  @MapsId
  private PersonEntity person;

  private String password;

  @Builder.Default
  @Column(nullable = false)
  private Boolean status = Boolean.TRUE;
}
