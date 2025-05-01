package com.paw.fund.app.modules.pet_management.repository.database.pet;

import com.paw.fund.app.modules.auditable_management.repository.database.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pets")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PetEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long petId;

    @Column
    Long shelterId;

    @Column
    Long petTypeId;

    @Column
    Long petBreedId;

    @Column
    String petCode;

    @Column
    String petName;

    @Column
    String colorCode;

    @Column
    String colorName;

    @Column
    String genderCode;

    @Column
    String genderName;

    @Column
    String description;

    @Column
    LocalDate dateOfBirth;

    @Column
    LocalDate receivedAt;

    @Column
    String statusCode;

    @Column
    String statusName;
}
