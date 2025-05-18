package com.paw.fund.app.modules.pet_intake_registration_management.repository.database;

import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationSearchCriteria;
import com.paw.fund.app.modules.pet_intake_registration_management.repository.database.dao.PetIntakeRegistrationActionDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPetIntakeRegistrationRepository extends JpaRepository<PetIntakeRegistrationEntity, Long> {

    @Query("""
        SELECT pir
        FROM PetIntakeRegistrationEntity pir
        WHERE pir.statusCode = :#{T(com.paw.fund.enums.EPetIntakeRegistrationStatus).NEW.getCode()}
    """)
    List<PetIntakeRegistrationEntity> findAllByStatusCodeNew();

    @Query("""
        SELECT pir
        FROM PetIntakeRegistrationEntity pir
        LEFT JOIN PetTypeEntity pt ON pt.petTypeId = pir.petTypeId
        WHERE (pir.statusCode = :#{T(com.paw.fund.enums.EPetIntakeRegistrationStatus).NEW.getCode()})
            AND (pir.createdAt BETWEEN :#{#searchCriteria.timeRange().get(0)} AND :#{#searchCriteria.timeRange().get(1)})
            AND (:#{#searchCriteria.isSearchNullOrEmpty()} = TRUE
                OR (pir.address ILIKE %:#{#searchCriteria.search()}%
                    OR pir.informerPhone ILIKE %:#{#searchCriteria.search()}%))
            AND (:#{#searchCriteria.isPetTypeCodesNullOrEmpty()} = TRUE
                OR pt.petTypeCode IN :#{#searchCriteria.petTypeCodes()})
            AND (:#{#searchCriteria.isReasonTypeCodesNullOrEmpty()} = TRUE
                OR pir.reasonTypeCode IN :#{#searchCriteria.reasonTypeCodes()})
    """)
    Page<PetIntakeRegistrationEntity> findAll(PetIntakeRegistrationSearchCriteria searchCriteria, Pageable pageable);

    @Query("""
        SELECT pir
        FROM PetIntakeRegistrationEntity pir
        WHERE pir.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND pir.petIntakeRegistrationId = :petIntakeRegistrationId
    """)
    Optional<PetIntakeRegistrationEntity> findByStatusCodeNotDeletedAndPetIntakeRegistrationId(Long petIntakeRegistrationId);

    @Query("""
        SELECT pir
        FROM PetIntakeRegistrationEntity pir
        WHERE pir.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND pir.informerPhone = :informerPhone
    """)
    List<PetIntakeRegistrationEntity> findByStatusCodeNotDeletedAndInformerPhone(String informerPhone);

    @Query("""
        SELECT 
            pir.statusCode = :#{T(com.paw.fund.enums.EPetIntakeRegistrationStatus).NEW.getCode()} AS allowUpdate,
            pir.statusCode = :#{T(com.paw.fund.enums.EPetIntakeRegistrationStatus).NEW.getCode()} AS allowDelete,
            pir.statusCode = :#{T(com.paw.fund.enums.EPetIntakeRegistrationStatus).NEW.getCode()} AS allowProcess,
            pir.statusCode = :#{T(com.paw.fund.enums.EPetIntakeRegistrationStatus).PROCESSING.getCode()} AS allowCancel,
            pir.statusCode = :#{T(com.paw.fund.enums.EPetIntakeRegistrationStatus).PROCESSING.getCode()} AS allowFinished
        FROM PetIntakeRegistrationEntity pir
        WHERE pir.petIntakeRegistrationId = :petIntakeRegistrationId
    """)
    Optional<PetIntakeRegistrationActionDAO> findPetIntakeRegistrationActionById(Long petIntakeRegistrationId);

    @Query("""
        SELECT 
            pir.petIntakeRegistrationId AS petIntakeRegistrationId,
            pir.statusCode = :#{T(com.paw.fund.enums.EPetIntakeRegistrationStatus).NEW.getCode()} AS allowUpdate,
            pir.statusCode = :#{T(com.paw.fund.enums.EPetIntakeRegistrationStatus).NEW.getCode()} AS allowDelete,
            pir.statusCode = :#{T(com.paw.fund.enums.EPetIntakeRegistrationStatus).NEW.getCode()} AS allowProcess,
            pir.statusCode = :#{T(com.paw.fund.enums.EPetIntakeRegistrationStatus).PROCESSING.getCode()} AS allowCancel,
            pir.statusCode = :#{T(com.paw.fund.enums.EPetIntakeRegistrationStatus).PROCESSING.getCode()} AS allowFinished
        FROM PetIntakeRegistrationEntity pir
    """)
    List<PetIntakeRegistrationActionDAO> findAllPetIntakeRegistrationAction();
}
