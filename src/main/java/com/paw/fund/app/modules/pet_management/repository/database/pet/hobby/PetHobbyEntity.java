package com.paw.fund.app.modules.pet_management.repository.database.pet.hobby;

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
@Table(name = "pet_hobby")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PetHobbyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long petHobbyId;

    @Column
    Long petId;

    @Column
    Long hobbyId;
}
