package com.paw.fund.app.modules.pet_management.service.pet;

import com.paw.fund.app.modules.pet_management.aspect.CreatePetHelper;
import com.paw.fund.app.modules.pet_management.aspect.GetPetDetailHelper;
import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetId;
import com.paw.fund.app.modules.pet_management.service.pet.usecase.IPetUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetUseCaseService implements IPetUseCase {
    @NonNull
    PetCommandService commandService;

    @NonNull
    PetQueryService queryService;

    @Override
    @Transactional
    @CreatePetHelper
    public Pet createPet(Pet pet) {
        return commandService.save(pet.withPetCode(UUID.randomUUID().toString()));
    }

    @Override
    @GetPetDetailHelper
    public Pet getPetDetail(PetId petId) {
        return queryService.findById(petId.value());
    }
}
