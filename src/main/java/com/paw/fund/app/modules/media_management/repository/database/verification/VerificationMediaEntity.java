package com.paw.fund.app.modules.media_management.repository.database.verification;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "verification_medias")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerificationMediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long verificationMediaId;

    @Column
    Long licenseId;

    @Column
    Long postAdoptionVerificationId;

    @Column(name = "adoption_request_id")
    Long adoptionRegistrationId;

    @Column(name = "pet_intake_request_id")
    Long petIntakeRegistrationId;

    @Column
    String url;

    @Column
    String mediaTypeCode;

    @Column
    String mediaTypeName;
}
