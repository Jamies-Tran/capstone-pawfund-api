package com.paw.fund.app.modules.form_management.repository.database.answer.option;

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
@Table(name = "answer_option")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerOptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long answerOptionId;

    @Column
    Long answerId;

    @Column
    Long optionId;
}
