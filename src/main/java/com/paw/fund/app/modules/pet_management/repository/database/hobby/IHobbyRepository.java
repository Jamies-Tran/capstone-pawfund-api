package com.paw.fund.app.modules.pet_management.repository.database.hobby;

import com.paw.fund.app.modules.pet_management.domain.hobby.usecase.HobbySearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IHobbyRepository extends JpaRepository<HobbyEntity, Long> {
    @Query("""
        SELECT COUNT(h) > 0
        FROM HobbyEntity h
        WHERE h.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND h.hobbyCode = :hobbyCode
    """)
    Boolean existsByStatusCodeNotDeletedAndHobbyCode(String hobbyCode);

    @Query("""
        SELECT COUNT(h) > 0
        FROM HobbyEntity h
        WHERE h.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND h.hobbyCode IN :hobbyCodes
    """)
    Boolean existsByStatusCodeNotDeletedAndHobbyCodeIn(List<String> hobbyCodes);

    @Query("""
        SELECT h
        FROM HobbyEntity h
        WHERE h.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND h.hobbyId = :hobbyId
    """)
    Optional<HobbyEntity> findByStatusCodeNotDeletedAndHobbyId(Long hobbyId);

    @Query("""
        SELECT h
        FROM HobbyEntity h
        WHERE (h.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()})
            AND (:#{#searchCriteria.isSearchNullOrEmpty()} = TRUE
                OR (h.hobbyName ILIKE %:#{#searchCriteria.search()}%
                    OR h.hobbyCode ILIKE %:#{#searchCriteria.search()}%))
            AND (:#{#searchCriteria.isStatusCodesNullOrEmpty()} = TRUE
                OR h.statusCode IN :#{#searchCriteria.statusCodes()})
            AND (:#{#searchCriteria.isPetTypeIdNullOrEmpty()} = TRUE
                OR h.petTypeId = :#{#searchCriteria.petTypeId()})    
    """)
    Page<HobbyEntity> findAll(HobbySearchCriteria searchCriteria, Pageable pageable);

    List<HobbyEntity> findAllByHobbyIdIn(List<Long> hobbyIds);
}
