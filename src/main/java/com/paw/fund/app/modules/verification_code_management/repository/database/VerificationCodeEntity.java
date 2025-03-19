package com.paw.fund.app.modules.verification_code_management.repository.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "verification_codes")
public class VerificationCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long verificationCodeId;

    @Column
    Long accountId;

    @Column
    String code;

    @Column
    String typeCode;

    @Column
    String typeName;

    @Column
    Boolean isUsed;

    @Column
    LocalDateTime expiredAt;

    @PrePersist
    public void prePersist() {
        if(Objects.isNull(code)) {
            Random random = new Random();
            int generatedCode = 100000 + random.nextInt(900000);
            code = String.valueOf(generatedCode);
        }

        if(Objects.isNull(isUsed)) {
            isUsed = false;
        }

        if(Objects.isNull(expiredAt)) {
            expiredAt = LocalDateTime.now().plusMinutes(15);
        }
    }
}
