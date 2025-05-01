package com.paw.fund.app.modules.pet_management.service.pet.hobby;

import com.paw.fund.app.modules.pet_management.domain.hobby.Hobby;
import com.paw.fund.app.modules.pet_management.domain.pet.hobby.IPetHobbyMapper;
import com.paw.fund.app.modules.pet_management.domain.pet.hobby.PetHobby;
import com.paw.fund.app.modules.pet_management.repository.database.pet.hobby.IPetHobbyRepository;
import com.paw.fund.app.modules.pet_management.repository.database.pet.hobby.PetHobbyEntity;
import com.paw.fund.app.modules.pet_management.service.hobby.HobbyQueryService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetHobbyQueryService {
    @NonNull
    IPetHobbyRepository repository;

    @NonNull
    HobbyQueryService hobbyQueryService;

    @NonNull
    IPetHobbyMapper mapper;

    public List<PetHobby> findAllByPetId(Long petId) {
        List<PetHobbyEntity> petHobbies = repository.findAllByPetId(petId);
        List<Long> hobbyIds = petHobbies.stream()
                .map(PetHobbyEntity::getHobbyId)
                .toList();
        Map<Long, Hobby> hobbies = hobbyQueryService.findAllByHobbyIdIn(hobbyIds)
                .stream()
                .collect(Collectors.toMap(Hobby::hobbyId, x -> x));

        return petHobbies.stream()
                .map(x -> mapper.toDto(x).withHobby(hobbies.computeIfAbsent(x.getHobbyId(), y -> null)))
                .toList();
    }
}
