package com.paw.fund.app.modules.pet_management.service.type.usecase;

import com.paw.fund.app.modules.pet_management.domain.type.PetType;
import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeFilter;
import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeId;
import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeUpdate;
import org.springframework.data.domain.Page;

public interface IPetTypeUseCase {
    PetType createPetType(PetType petType);

    PetType getPetTypeDetail(PetTypeId petTypeId);

    PetType updatePetType(PetTypeUpdate petTypeUpdate);

    void deletePetType(PetTypeId petTypeId);

    Page<PetType> getPetTypeList(PetTypeFilter filter);
}
