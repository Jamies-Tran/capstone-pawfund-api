package com.paw.fund.app.modules.form_management.repository.database.form.reply;

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

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "form_responses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long formResponseId;

    @Column
    Long accountId;

    @Column
    Long formId;

    @Column
    LocalDateTime responseAt;
}
