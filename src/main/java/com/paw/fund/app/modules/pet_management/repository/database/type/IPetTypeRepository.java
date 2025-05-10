package com.paw.fund.app.modules.pet_management.repository.database.type;

import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPetTypeRepository extends JpaRepository<PetTypeEntity, Long> {
    @Query("""
        SELECT COUNT(p) > 0
        FROM PetTypeEntity p
        WHERE p.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND p.petTypeCode = :petTypeCode
    """)
    Boolean existsByStatusCodeNotDeletedAndPetTypeCode(String petTypeCode);

    @Query("""
        SELECT p
        FROM PetTypeEntity p
        WHERE p.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND p.petTypeId = :petTypeId
    """)
    Optional<PetTypeEntity> findByStatusCodeNotDeletedAndPetTypeId(Long petTypeId);

    @Query("""
        SELECT p
        FROM PetTypeEntity p
        WHERE (p.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()})
            AND (:#{#searchCriteria.isSearchNullOrEmpty()} = TRUE
                OR (p.petTypeCode ILIKE %:#{#searchCriteria.search()}%
                    OR p.petTypeName ILIKE %:#{#searchCriteria.search()}%))
            AND (:#{#searchCriteria.isStatusCodesNullOrEmpty()} = TRUE
                OR p.statusCode IN :#{#searchCriteria.statusCodes()})
    """)
    Page<PetTypeEntity> findAll(PetTypeSearchCriteria searchCriteria, Pageable pageable);

    @Query("""
        SELECT COUNT(p) > 0
        FROM PetTypeEntity p
        WHERE p.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND p.petTypeCode IN :petTypeCodes
    """)
    Boolean existsByStatusCodeNotDeletedAndPetTypeCodeIn(List<String> petTypeCodes);

    List<PetTypeEntity> findAllByPetTypeIdIn(List<Long> petTypeIds);
}
