package com.paw.fund.app.modules.pet_management.repository.database.breed;

import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPetBreedRepository extends JpaRepository<PetBreedEntity, Long> {
    @Query("""
        SELECT p
        FROM PetBreedEntity p
        WHERE (p.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()})
            AND (:#{#searchCriteria.isPetTypeIdNullOrEmpty()} = TRUE
                OR p.petTypeId = :#{#searchCriteria.petTypeId()})
            AND (:#{#searchCriteria.isSearchNullOrEmpty()} = TRUE
                OR (p.breedCode ILIKE %:#{#searchCriteria.search()}%
                    OR p.breedName ILIKE %:#{#searchCriteria.search()}%))
            AND (:#{#searchCriteria.isStatusCodesNullOrEmpty()} = TRUE
                OR p.statusCode IN :#{#searchCriteria.statusCodes()})
    """)
    Page<PetBreedEntity> findAll(PetBreedSearchCriteria searchCriteria, Pageable pageable);

    @Query("""
        SELECT p
        FROM PetBreedEntity p
        WHERE p.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND p.petBreedId = :petBreedId
    """)
    Optional<PetBreedEntity> findByStatusCodeNotDeletedAndPetBreedId(Long petBreedId);

    @Query("""
        SELECT COUNT(p) > 0
        FROM PetBreedEntity p
        WHERE p.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND p.breedCode = :breedCode
    """)
    Boolean existsByStatusCodeNotDeletedAndBreedCode(String breedCode);

    @Query("""
        SELECT COUNT(p) > 0
        FROM PetBreedEntity p
        WHERE p.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND p.breedCode IN :breedCodes
    """)
    Boolean existsByStatusCodeNotDeletedAndBreedCodeIn(List<String> breedCodes);

    List<PetBreedEntity> findAllByPetBreedIdIn(List<Long> petBreedIds);
}
