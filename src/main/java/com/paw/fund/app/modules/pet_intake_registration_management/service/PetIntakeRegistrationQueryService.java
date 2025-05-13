package com.paw.fund.app.modules.pet_intake_registration_management.service;

import com.paw.fund.app.modules.pet_intake_registration_management.domain.IPetIntakeRegistrationMapper;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationSearchCriteria;
import com.paw.fund.app.modules.pet_intake_registration_management.repository.database.IPetIntakeRegistrationRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.utils.request.PageRequestCustom;
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
public class PetIntakeRegistrationQueryService {
    @NonNull
    IPetIntakeRegistrationRepository repository;

    @NonNull
    IPetIntakeRegistrationMapper mapper;

    public List<PetIntakeRegistration> findAllByStatusCodeNew() {
        return repository.findAllByStatusCodeNew().stream()
                .map(mapper::toDto)
                .toList();
    }

    public PetIntakeRegistration findByPetIntakeRegistrationId(Long petIntakeRegistrationId) {
        return repository.findById(petIntakeRegistrationId)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Page<PetIntakeRegistration> findAll(PetIntakeRegistrationSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(mapper::toDto);
    }

    public PetIntakeRegistration findByPetIntakeRegistrationInformerPhone(String phone) {
        return repository.findByStatusCodeNotDeletedAndInformerPhone(phone)
                .map(mapper::toDto)
                .orElse(null);
    }
}
