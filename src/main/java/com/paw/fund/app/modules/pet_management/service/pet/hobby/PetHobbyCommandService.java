package com.paw.fund.app.modules.pet_management.service.pet.hobby;

import com.paw.fund.app.modules.pet_management.domain.pet.hobby.IPetHobbyMapper;
import com.paw.fund.app.modules.pet_management.domain.pet.hobby.PetHobby;
import com.paw.fund.app.modules.pet_management.repository.database.pet.hobby.IPetHobbyRepository;
import com.paw.fund.app.modules.pet_management.repository.database.pet.hobby.PetHobbyEntity;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetHobbyCommandService {
    @NonNull
    IPetHobbyRepository repository;

    @NonNull
    IPetHobbyMapper mapper;

    public List<PetHobby> saveAllWithPetId(Long petId, List<PetHobby> petHobbies) {
        List<PetHobbyEntity> newPetHobby = petHobbies.stream()
                .map(x -> mapper.toEntity(x.withPetId(petId)))
                .toList();
        List<PetHobbyEntity> savePetHobby = repository.saveAll(newPetHobby);

        return savePetHobby.stream().map(mapper::toDto).toList();
    }
}
