package com.paw.fund.app.modules.pet_management.service.type;

import com.paw.fund.app.modules.pet_management.domain.type.PetType;
import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeFilter;
import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeId;
import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeUpdate;
import com.paw.fund.app.modules.pet_management.service.type.usecase.IPetTypeUseCase;
import com.paw.fund.enums.EPetInformationStatus;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetTypeUseCaseService implements IPetTypeUseCase {
    @NonNull
    PetTypeCommandService commandService;

    @NonNull
    PetTypeQueryService queryService;

    @Override
    @Transactional
    public PetType createPetType(PetType petType) {
        return commandService.save(petType);
    }

    @Override
    public PetType getPetTypeDetail(PetTypeId petTypeId) {
        return queryService.findById(petTypeId.value());
    }

    @Override
    @Transactional
    public PetType updatePetType(PetTypeUpdate petTypeUpdate) {
        return commandService.update(petTypeUpdate.petTypeId(), petTypeUpdate.petType());
    }

    @Override
    public void deletePetType(PetTypeId petTypeId) {
        commandService.delete(petTypeId.value());
    }

    @Override
    public Page<PetType> getPetTypeList(PetTypeFilter filter) {
        return queryService.findAll(filter.searchCriteria(), filter.pageRequestCustom());
    }

    @Override
    public PetType activePetType(PetTypeId petTypeId) {
        return commandService.updateStatus(petTypeId.value(), EPetInformationStatus.ACTIVE);
    }

    @Override
    public PetType blockPetType(PetTypeId petTypeId) {
        return commandService.updateStatus(petTypeId.value(), EPetInformationStatus.BLOCK);
    }
}
