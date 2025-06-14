package com.paw.fund.app.modules.pet_management.service.pet;


import com.paw.fund.app.modules.pet_management.domain.pet.IPetMapper;
import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import com.paw.fund.app.modules.pet_management.repository.database.pet.IPetRepository;
import com.paw.fund.app.modules.pet_management.repository.database.pet.PetEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.enums.EDeleteStatus;
import com.paw.fund.enums.EPetStatus;
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

    public Pet update(Long petId, Pet pet) {
        ValidationUtil.validateNotNullPointerException(pet);

        return repository.findByStatusCodeNotDeletedAndPetId(petId)
                .map(x -> {
                    mapper.update(x, pet);
                    PetEntity savePet = repository.save(x);

                    return mapper.toDto(savePet);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Pet updateStatus(Long petId, EPetStatus status) {
        ValidationUtil.validateArgumentNotNull(petId);
        ValidationUtil.validateArgumentNotNull(status);

        return repository.findByStatusCodeNotDeletedAndPetId(petId)
                .map(pet -> {
                    pet.setStatusCode(status.getCode());
                    pet.setStatusName(status.getName());
                    PetEntity updatedPet = repository.save(pet);

                    return mapper.toDto(updatedPet);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    public void delete(Long petId) {
        ValidationUtil.validateArgumentNotNull(petId);

        repository.findByStatusCodeNotDeletedAndPetId(petId)
                .ifPresentOrElse(
                        x -> {
                            x.setStatusCode(EDeleteStatus.DELETED.getCode());
                            x.setStatusName(EDeleteStatus.DELETED.getName());

                            repository.save(x);
                        },
                        () -> {
                            throw new ResourceNotFoundException();
                        }
                );
    }
}
