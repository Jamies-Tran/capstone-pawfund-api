package com.paw.fund.app.modules.pet_management.repository.database.hobby;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hobbies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HobbyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long hobbyId;

    @Column
    Long petTypeId;

    @Column
    String hobbyCode;

    @Column
    String hobbyName;

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
