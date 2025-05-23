package com.paw.fund.app.modules.pet_management.repository.database.pet;

import com.paw.fund.app.modules.auditable_management.repository.database.AuditableEntity;
import com.paw.fund.enums.EReceiveSource;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    @Column(name = "pet_intake_request_id")
    Long petIntakeRegistrationId;

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
    String receiveSourceCode;

    @Column
    String receiveSourceName;

    @Column
    BigDecimal adoptionCost;

    @Column
    String statusCode;

    @Column
    String statusName;

    @PrePersist
    private void prePersist() {
        if(Objects.nonNull(petCode)) {
            petCode = "PET_%s_%s".formatted(getCreatedAt()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd")), shelterId.toString());
        }

        if(Objects.nonNull(petIntakeRegistrationId)) {
            receiveSourceCode = EReceiveSource.CUSTOMER.getCode();
            receiveSourceName = EReceiveSource.CUSTOMER.getName();
        } else {
            receiveSourceCode = EReceiveSource.STAFF.getCode();
            receiveSourceName = EReceiveSource.STAFF.getName();
        }
    }
}
