package com.paw.fund.app.modules.pet_management.service.breed;

import com.paw.fund.app.modules.pet_management.domain.breed.IPetBreedMapper;
import com.paw.fund.app.modules.pet_management.domain.breed.PetBreed;
import com.paw.fund.app.modules.pet_management.repository.database.breed.IPetBreedRepository;
import com.paw.fund.app.modules.pet_management.repository.database.breed.PetBreedEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceDuplicateException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.enums.EDeleteStatus;
import com.paw.fund.enums.EPetInformationStatus;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
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
        if(repository.existsByStatusCodeNotDeletedAndBreedCode(petBreed.breedCode())) {
            throw new ResourceDuplicateException("Mã giống thú cưng đã tồn tại");
        }
    }

    public PetBreed update(Long petBreedId, PetBreed petBreed) {
        ValidationUtil.validateArgumentNotNull(petBreedId);
        ValidationUtil.validateNotNullPointerException(petBreed);

        return repository.findByStatusCodeNotDeletedAndPetBreedId(petBreedId)
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
                && repository.existsByStatusCodeNotDeletedAndBreedCode(newPetBreed.breedCode())) {
            throw new ResourceDuplicateException("Mã giống thú cưng đã tồn tại");
        }
    }

    public void delete(Long petBreedId) {
        ValidationUtil.validateArgumentNotNull(petBreedId);

        repository.findByStatusCodeNotDeletedAndPetBreedId(petBreedId)
                .ifPresentOrElse(
                        x -> {
                            x.setStatusCode(EDeleteStatus.DELETED.getCode());
                            x.setStatusName(EDeleteStatus.DELETED.getName());

                            repository.save(x);
                        },
                        () -> {
                            throw new ResourceNotFoundException();
                        });
    }

    public PetBreed updateStatus(Long petBreedId, EPetInformationStatus status) {
        return repository.findByStatusCodeNotDeletedAndPetBreedId(petBreedId)
                .map(x -> {
                    x.setStatusCode(status.getCode());
                    x.setStatusName(status.getName());
                    PetBreedEntity updatePetBreed = repository.save(x);

                    return mapper.toDto(updatePetBreed);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    List<PetBreed> saveAll(List<PetBreed> petBreeds) {
        ValidationUtil.validateArgumentListNotNull(petBreeds);
        validateSaveList(petBreeds);

        List<PetBreedEntity> newPetBreeds = petBreeds.stream().map(mapper::toEntity).toList();
        List<PetBreedEntity> savedPetBreeds = repository.saveAll(newPetBreeds);

        return savedPetBreeds.stream()
                .map(mapper::toDto)
                .toList();
    }

    private void validateSaveList(List<PetBreed> petBreeds) {
        List<String> codes = petBreeds.stream()
                .map(PetBreed::breedCode)
                .toList();
        if(repository.existsByStatusCodeNotDeletedAndBreedCodeIn(codes)) {
            throw new ResourceDuplicateException("Mã giống thú cưng đã tồn tại");
        }
    }
}
