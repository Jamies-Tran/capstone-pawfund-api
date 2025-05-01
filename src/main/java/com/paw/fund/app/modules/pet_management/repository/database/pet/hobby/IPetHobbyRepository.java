package com.paw.fund.app.modules.pet_management.repository.database.pet.hobby;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPetHobbyRepository extends JpaRepository<PetHobbyEntity, Long> {
    List<PetHobbyEntity> findAllByPetId(Long petId);
}
