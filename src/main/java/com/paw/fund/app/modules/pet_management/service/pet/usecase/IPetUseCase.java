package com.paw.fund.app.modules.pet_management.service.pet.usecase;

import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetFilter;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetId;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetUpdate;
import org.springframework.data.domain.Page;

public interface IPetUseCase {
    Pet createPet(Pet pet);

    Pet getPetDetail(PetId petId);

    Pet updatePet(PetUpdate petUpdate);

    Page<Pet> getPetList(PetFilter filter);

    void deletePet(PetId petId);
}
