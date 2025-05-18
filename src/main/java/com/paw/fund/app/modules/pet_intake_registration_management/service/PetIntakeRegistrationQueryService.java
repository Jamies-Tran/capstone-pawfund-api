package com.paw.fund.app.modules.pet_intake_registration_management.service;

import com.paw.fund.app.modules.pet_intake_registration_management.domain.IPetIntakeRegistrationMapper;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistrationAction;
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
import java.util.Map;
import java.util.stream.Collectors;

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
        PetIntakeRegistrationAction action = repository.findPetIntakeRegistrationActionById(petIntakeRegistrationId)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
        return repository.findById(petIntakeRegistrationId)
                .map(x -> mapper.toDto(x).withAction(action))
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Page<PetIntakeRegistration> findAll(PetIntakeRegistrationSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        Map<Long, PetIntakeRegistrationAction>  petIntakeRegistrationActionMap = repository.findAllPetIntakeRegistrationAction()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toMap(PetIntakeRegistrationAction::petIntakeRegistrationId, action -> action));

        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(x -> mapper.toDto(x)
                        .withAction(petIntakeRegistrationActionMap.computeIfAbsent(x.getPetIntakeRegistrationId(), _ -> PetIntakeRegistrationAction.ofDefault())));
    }

    public List<PetIntakeRegistration> findByPetIntakeRegistrationInformerPhone(String phone) {
        return repository.findByStatusCodeNotDeletedAndInformerPhone(phone).stream()
                .map(mapper::toDto)
                .toList();
    }
}
