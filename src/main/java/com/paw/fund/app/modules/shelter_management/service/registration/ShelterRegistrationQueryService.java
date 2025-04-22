package com.paw.fund.app.modules.shelter_management.service.registration;

import com.paw.fund.app.modules.shelter_management.domain.registration.IShelterRegistrationMapper;
import com.paw.fund.app.modules.shelter_management.domain.registration.ShelterRegistration;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationSearchCriteria;
import com.paw.fund.app.modules.shelter_management.repository.database.registration.IShelterRegistrationRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.enums.EShelterRegistrationStatus;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterRegistrationQueryService {
    @NonNull
    IShelterRegistrationRepository repository;

    @NonNull
    IShelterRegistrationMapper mapper;

    public Page<ShelterRegistration> findAll(ShelterRegistrationSearchCriteria searchCriteria,
                                             PageRequestCustom pageRequestCustom) {
        ValidationUtil.validateNotNullPointerException(searchCriteria);
        ValidationUtil.validateNotNullPointerException(pageRequestCustom);

        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(mapper::toDto);
    }

    public Boolean existsApprovedShelterRegistrationByShelterId(Long shelterId) {
        ValidationUtil.validateArgumentNotNull(shelterId);

        return repository.existsByApprovedShelterRegistrationByShelterId(shelterId);
    }

    public Boolean existsByAccountIdAndStatusIn(Long accountId, List<EShelterRegistrationStatus> statuses) {
        ValidationUtil.validateArgumentNotNull(accountId);
        ValidationUtil.validateArgumentListNotNull(statuses);

        List<String> statusCodes = statuses.stream().map(EShelterRegistrationStatus::getCode).toList();
        return repository.existsByAccountIdAndStatusCodeIn(accountId, statusCodes);
    }
}
