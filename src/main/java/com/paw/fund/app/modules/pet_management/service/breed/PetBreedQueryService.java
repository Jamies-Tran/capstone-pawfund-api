package com.paw.fund.app.modules.pet_management.service.breed;

import com.paw.fund.app.modules.pet_management.domain.breed.IPetBreedMapper;
import com.paw.fund.app.modules.pet_management.domain.breed.PetBreed;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedSearchCriteria;
import com.paw.fund.app.modules.pet_management.repository.database.breed.IPetBreedRepository;
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
public class PetBreedQueryService {
    @NonNull
    IPetBreedRepository repository;

    @NonNull
    IPetBreedMapper mapper;

    public PetBreed findById(Long petBreedId) {
        return repository.findByStatusCodeNotDeletedAndPetBreedId(petBreedId)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Page<PetBreed> findAll(PetBreedSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(mapper::toDto);
    }

    public List<PetBreed> findAllByPetBreedIdIn(List<Long> petBreedIds) {
        return repository.findAllByPetBreedIdIn(petBreedIds).stream()
                .map(mapper::toDto)
                .toList();
    }
}
