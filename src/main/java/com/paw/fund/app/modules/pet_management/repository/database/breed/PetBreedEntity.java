package com.paw.fund.app.modules.pet_management.repository.database.breed;

import com.paw.fund.enums.EPetInformationStatus;
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
import org.springframework.util.StringUtils;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pet_breeds")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PetBreedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long petBreedId;

    @Column
    Long petTypeId;

    @Column
    String breedCode;

    @Column
    String breedName;

    @Column
    String statusCode;

    @Column
    String statusName;

    @PrePersist
    private void prePersist() {
        if(!StringUtils.hasText(statusCode)) {
            statusCode = EPetInformationStatus.ACTIVE.getCode();
            statusName = EPetInformationStatus.ACTIVE.getName();
        }
    }
}
