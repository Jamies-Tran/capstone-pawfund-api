package com.paw.fund.app.modules.pet_management.service.breed.usecase;

import com.paw.fund.app.modules.pet_management.domain.breed.PetBreed;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedFilter;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedId;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedUpdate;
import org.springframework.data.domain.Page;

public interface IPetBreedUseCase {
    PetBreed createPetBreed(PetBreed petBreed);

    PetBreed getPetBreedDetail(PetBreedId petBreedId);

    Page<PetBreed> getPetBreedList(PetBreedFilter filter);

    PetBreed updatePetBreed(PetBreedUpdate petBreedUpdate);

    void deletePetBreed(PetBreedId petBreedId);

    PetBreed activePetBreed(PetBreedId petBreedId);

    PetBreed blockPetBreed(PetBreedId petBreedId);
}
