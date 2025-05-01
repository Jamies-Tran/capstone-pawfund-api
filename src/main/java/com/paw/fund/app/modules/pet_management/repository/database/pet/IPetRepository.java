package com.paw.fund.app.modules.pet_management.repository.database.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPetRepository extends JpaRepository<PetEntity, Long> {
    @Query("""
        SELECT p
        FROM PetEntity p
        WHERE p.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND p.petId = :petId
    """)
    Optional<PetEntity> findAllByStatusCodeNotDeletedAndPetId(Long petId);
}
