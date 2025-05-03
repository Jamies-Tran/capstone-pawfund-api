package com.paw.fund.app.modules.pet_management.service.pet;

import com.paw.fund.app.modules.pet_management.domain.pet.IPetMapper;
import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetSearchCriteria;
import com.paw.fund.app.modules.pet_management.repository.database.pet.IPetRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.utils.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetQueryService {
    @NonNull
    IPetRepository repository;

    @NonNull
    IPetMapper mapper;

    public Pet findById(Long petId) {
        return repository.findByStatusCodeNotDeletedAndPetId(petId)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Page<Pet> findAll(PetSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {

        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(mapper::toDto);
    }
}
