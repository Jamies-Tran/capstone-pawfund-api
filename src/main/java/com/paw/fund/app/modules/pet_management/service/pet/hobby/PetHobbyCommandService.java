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
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<PetHobby> updateAllByPetId(Long petId, List<PetHobby> petHobbies) {
        List<PetHobbyEntity> existedPetHobby = repository.findAllByPetId(petId);
        List<Long> deletedIds = existedPetHobby.stream()
                .map(PetHobbyEntity::getPetHobbyId)
                .filter(x -> petHobbies.stream().noneMatch(xx -> Optional.ofNullable(xx.petHobbyId())
                        .orElse(Long.MIN_VALUE).equals(x)))
                .toList();
        repository.deleteAllById(deletedIds);

        Map<Long, PetHobbyEntity> existedPetHobbyMap = existedPetHobby.stream()
                .collect(Collectors.toMap(PetHobbyEntity::getPetHobbyId, x -> x));
        List<PetHobbyEntity> newPetHobbies = petHobbies.stream()
                .map(x -> {
                    PetHobbyEntity petHobby;
                    if(Objects.nonNull(x.petHobbyId())) {
                        petHobby = existedPetHobbyMap.computeIfAbsent(x.petHobbyId(), _ -> {
                            PetHobbyEntity altPetHobby = mapper.toEntity(x.withPetId(petId));
                            altPetHobby.setPetHobbyId(null);

                            return altPetHobby;
                        });
                    } else {
                        petHobby = mapper.toEntity(x.withPetId(petId));
                    }
                    mapper.update(petHobby, x);
                    return petHobby;
                })
                .toList();
        List<PetHobbyEntity> savePetHobbies = repository.saveAll(newPetHobbies);

        return savePetHobbies.stream()
                .map(mapper::toDto)
                .toList();
    }
}
