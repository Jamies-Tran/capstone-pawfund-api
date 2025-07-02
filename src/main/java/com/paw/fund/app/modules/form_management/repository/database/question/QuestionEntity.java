package com.paw.fund.app.modules.form_management.repository.database.question;

import com.paw.fund.common.context.auditor.repository.database.AuditableEntity;
import com.paw.fund.enums.EFormStatus;
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

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "questions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long questionId;

    @Column
    Long formId;

    @Column
    String questionText;

    @Column
    String questionTypeCode;

    @Column
    String questionTypeName;

    @Column
    String statusCode;

    @Column
    String statusName;

    @PrePersist
    public void prePersist() {
        if(Objects.isNull(statusCode)) {
            statusCode = EFormStatus.ENABLE.getCode();
            statusName = EFormStatus.ENABLE.getName();
        }
    }
}
