package com.paw.fund.app.modules.pet_management.service.pet.usecase;

import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetId;

public interface IPetUseCase {
    Pet createPet(Pet pet);

    Pet getPetDetail(PetId petId);
}
