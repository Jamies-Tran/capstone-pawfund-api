package com.paw.fund.app.modules.pet_management.service.breed;

import com.paw.fund.app.modules.pet_management.domain.breed.PetBreed;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedFilter;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedId;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedList;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedUpdate;
import com.paw.fund.app.modules.pet_management.service.breed.usecase.IPetBreedUseCase;
import com.paw.fund.enums.EPetInformationStatus;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetBreedUseCaseService implements IPetBreedUseCase {
    @NonNull
    PetBreedCommandService commandService;

    @NonNull
    PetBreedQueryService queryService;

    @Override
    @Transactional
    public PetBreed createPetBreed(PetBreed petBreed) {
        return commandService.save(petBreed);
    }

    @Override
    public PetBreed getPetBreedDetail(PetBreedId petBreedId) {
        return queryService.findById(petBreedId.value());
    }

    @Override
    public Page<PetBreed> getPetBreedList(PetBreedFilter filter) {
        return queryService.findAll(filter.searchCriteria(), filter.pageRequestCustom());
    }

    @Override
    @Transactional
    public PetBreed updatePetBreed(PetBreedUpdate petBreedUpdate) {
        return commandService.update(petBreedUpdate.petBreedId(), petBreedUpdate.petBreed());
    }

    @Override
    @Transactional
    public void deletePetBreed(PetBreedId petBreedId) {
        commandService.delete(petBreedId.value());
    }

    @Override
    @Transactional
    public PetBreed activePetBreed(PetBreedId petBreedId) {
        return commandService.updateStatus(petBreedId.value(), EPetInformationStatus.ACTIVE);
    }

    @Override
    @Transactional
    public PetBreed blockPetBreed(PetBreedId petBreedId) {
        return commandService.updateStatus(petBreedId.value(), EPetInformationStatus.BLOCK);
    }

    @Override
    @Transactional
    public List<PetBreed> createPetBreedList(PetBreedList petBreedList) {
        return commandService.saveAll(petBreedList.list());
    }
}
