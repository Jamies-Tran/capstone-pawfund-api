package com.paw.fund.app.modules.pet_management.service.pet;

import com.paw.fund.app.modules.pet_management.domain.pet.IPetMapper;
import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import com.paw.fund.app.modules.pet_management.repository.database.pet.IPetRepository;
import com.paw.fund.app.modules.pet_management.repository.database.pet.PetEntity;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetCommandService {
    @NonNull
    IPetRepository repository;

    @NonNull
    IPetMapper mapper;

    public Pet save(Pet pet) {
        ValidationUtil.validateNotNullPointerException(pet);

        PetEntity newPet = mapper.toEntity(pet);
        PetEntity savedPet = repository.save(newPet);

        return mapper.toDto(savedPet);
    }
}
