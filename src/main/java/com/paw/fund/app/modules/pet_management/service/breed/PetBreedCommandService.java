package com.paw.fund.app.modules.pet_management.service.breed;

import com.paw.fund.app.modules.pet_management.domain.breed.IPetBreedMapper;
import com.paw.fund.app.modules.pet_management.domain.breed.PetBreed;
import com.paw.fund.app.modules.pet_management.repository.database.breed.IPetBreedRepository;
import com.paw.fund.app.modules.pet_management.repository.database.breed.PetBreedEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceDuplicateException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetBreedCommandService {
    @NonNull
    IPetBreedRepository repository;

    @NonNull
    IPetBreedMapper mapper;

    public PetBreed save(PetBreed petBreed) {
        ValidationUtil.validateNotNullPointerException(petBreed);
        validateSave(petBreed);

        PetBreedEntity newPetBreed = mapper.toEntity(petBreed);
        PetBreedEntity savePetBreed = repository.save(newPetBreed);

        return mapper.toDto(savePetBreed);
    }

    private void validateSave(PetBreed petBreed) {
        if(repository.existsByBreedCode(petBreed.breedCode())) {
            throw new ResourceDuplicateException("Mã thú cưng đã tồn tại");
        }
    }

    public PetBreed update(Long petBreedId, PetBreed petBreed) {
        return repository.findById(petBreedId)
                .map(x -> {
                    validateUpdate(x, petBreed);
                    mapper.update(x, petBreed);
                    PetBreedEntity savePetBreed = repository.save(x);

                    return mapper.toDto(savePetBreed);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    private void validateUpdate(PetBreedEntity existedPetBreed, PetBreed newPetBreed) {
        if(!Objects.equals(existedPetBreed.getBreedCode(), newPetBreed.breedCode())
                && repository.existsByBreedCode(newPetBreed.breedCode())) {
            throw new ResourceDuplicateException("Mã thú cưng đã tồn tại");
        }
    }
}
