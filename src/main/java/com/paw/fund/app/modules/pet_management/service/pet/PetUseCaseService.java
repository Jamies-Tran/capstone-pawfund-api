package com.paw.fund.app.modules.pet_management.service.pet;

import com.paw.fund.app.modules.log_management.annotation.CreatePetActivityLogHelper;
import com.paw.fund.app.modules.pet_management.aspect.CreatePetHelper;
import com.paw.fund.app.modules.pet_management.aspect.GetPetDetailHelper;
import com.paw.fund.app.modules.pet_management.aspect.GetPetListHelper;
import com.paw.fund.app.modules.pet_management.aspect.UpdatePetHelper;
import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetFilter;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetId;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetUpdate;
import com.paw.fund.app.modules.pet_management.service.pet.usecase.IPetUseCase;
import com.paw.fund.enums.EPetAction;
import com.paw.fund.enums.EPetStatus;
import com.paw.fund.enums.EReceiveSource;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
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
    @CreatePetActivityLogHelper(action = EPetAction.CREATED)
    public Pet createPet(Pet pet) {
        return commandService.save(
                pet
                        .withPetCode(UUID.randomUUID().toString())
                        .withReceiveSourceCode(EReceiveSource.STAFF.getCode())
                        .withReceiveSourceName(EReceiveSource.STAFF.getName()));
    }

    @Override
    @GetPetDetailHelper
    public Pet getPetDetail(PetId petId) {
        return queryService.findById(petId.value());
    }

    @Override
    @Transactional
    @UpdatePetHelper
    @CreatePetActivityLogHelper(action = EPetAction.UPDATED)
    public Pet updatePet(PetUpdate petUpdate) {
        return commandService.update(petUpdate.petId(), petUpdate.pet());
    }

    @Override
    @GetPetListHelper
    public Page<Pet> getPetList(PetFilter filter) {
        return queryService.findAll(filter.searchCriteria(), filter.pageRequestCustom());
    }

    @Override
    @Transactional
    @CreatePetActivityLogHelper(action = EPetAction.DELETED)
    public void deletePet(PetId petId) {
        commandService.delete(petId.value());
    }

    @Override
    @Transactional
    @CreatePetActivityLogHelper(action = EPetAction.UPDATED_STATUS, status = EPetStatus.ADOPTABLE)
    public Pet setAdoptablePet(PetId petId) {
        return commandService.updateStatus(petId.value(), EPetStatus.ADOPTABLE);
    }

    @Override
    @CreatePetActivityLogHelper(action = EPetAction.UPDATED_STATUS, status = EPetStatus.NOT_ADOPTABLE)
    public Pet setNotadoptablePet(PetId petId) {
        return commandService.updateStatus(petId.value(), EPetStatus.NOT_ADOPTABLE);
    }


}
