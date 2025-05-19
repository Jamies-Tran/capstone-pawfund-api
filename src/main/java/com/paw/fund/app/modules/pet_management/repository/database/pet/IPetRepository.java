package com.paw.fund.app.modules.pet_management.repository.database.pet;

import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetSearchCriteria;
import com.paw.fund.app.modules.pet_management.repository.database.pet.dao.PetSummarizeInfoDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPetRepository extends JpaRepository<PetEntity, Long> {
    @Query("""
        SELECT p
        FROM PetEntity p
        WHERE p.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND p.petId = :petId
    """)
    Optional<PetEntity> findByStatusCodeNotDeletedAndPetId(Long petId);

    @Query("""
        SELECT p
        FROM PetEntity p
        LEFT JOIN PetTypeEntity pt ON p.petTypeId = pt.petTypeId
        LEFT JOIN PetBreedEntity pb ON p.petBreedId = pb.petBreedId
        WHERE (p.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()})
            AND (:#{#searchCriteria.isSearchNullOrEmpty()} = TRUE
                OR (p.petCode ILIKE %:#{#searchCriteria.search()}%
                    OR p.petName ILIKE %:#{#searchCriteria.search()}%))
            AND (:#{#searchCriteria.isShelterIdNullOrEmpty()} = TRUE
                OR p.shelterId = :#{#searchCriteria.shelterId()})
            AND (p.createdAt BETWEEN :#{#searchCriteria.timeRange().get(0)}
                AND :#{#searchCriteria.timeRange().get(1)})
            AND (:#{#searchCriteria.isReceivedAtTimeRangeNullOrTimeRange()} = TRUE
                OR p.receivedAt BETWEEN :#{#searchCriteria.getReceivedAtMin()} AND :#{#searchCriteria.getReceivedAtMax()})
            AND (:#{#searchCriteria.isDateOfBirthTimeRangeNullOrTimeRange()} = TRUE
                OR p.dateOfBirth BETWEEN :#{#searchCriteria.getDateOfBirthMin()} AND :#{#searchCriteria.getDateOfBirthMax()})
            AND (:#{#searchCriteria.isPetTypeCodesNullOrEmpty()} = TRUE
                OR pt.petTypeCode IN :#{#searchCriteria.petTypeCodes()})
            AND (:#{#searchCriteria.isPetBreedCodesNullOrEmpty()} = TRUE
                OR pb.breedCode IN :#{#searchCriteria.petBreedCodes()})
            AND (:#{#searchCriteria.isReceiveSourceCodesNullOrEmpty()} = TRUE
                OR p.receiveSourceCode IN :#{#searchCriteria.receiveSourceCodes()})
            AND (:#{#searchCriteria.isPetHobbyCodesNullOrEmpty()} = TRUE
                OR EXISTS 
                (
                    SELECT 1 FROM HobbyEntity e
                    LEFT JOIN PetHobbyEntity ph ON e.hobbyId = ph.hobbyId
                    WHERE ph.petId = p.petId
                        AND e.hobbyCode IN :#{#searchCriteria.petHobbyCodes()}
                )
            )
    """)
    Page<PetEntity> findAll(PetSearchCriteria searchCriteria, Pageable pageable);

    @Query("""
        SELECT 
            p.shelterId AS shelterId,
            COUNT(p) AS total,
            pt.petTypeCode AS petTypeCode,
            pt.petTypeName AS petTypeName
        FROM PetEntity p
        INNER JOIN PetTypeEntity pt ON p.petTypeId = pt.petTypeId
        WHERE p.shelterId IN :shelterIds
        GROUP BY p.shelterId, pt.petTypeCode, pt.petTypeName
    """)
    List<PetSummarizeInfoDAO> findAllPetSummarizeInfoByShelterIdIn(List<Long> shelterIds);
}
