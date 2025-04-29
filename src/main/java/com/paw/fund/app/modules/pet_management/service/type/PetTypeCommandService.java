package com.paw.fund.app.modules.pet_management.service.type;

import com.paw.fund.app.modules.pet_management.domain.type.IPetTypeMapper;
import com.paw.fund.app.modules.pet_management.domain.type.PetType;
import com.paw.fund.app.modules.pet_management.repository.database.type.IPetTypeRepository;
import com.paw.fund.app.modules.pet_management.repository.database.type.PetTypeEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceDuplicateException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.enums.EDeleteStatus;
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
public class PetTypeCommandService {
    @NonNull
    IPetTypeRepository repository;

    @NonNull
    IPetTypeMapper mapper;

    public PetType save(PetType petType) {
        ValidationUtil.validateNotNullPointerException(petType);
        validateSave(petType);

        PetTypeEntity newPetType = mapper.toEntity(petType);
        PetTypeEntity savePetType = repository.save(newPetType);

        return mapper.toDto(savePetType);
    }

    private void validateSave(PetType petType) {
        if(repository.existsByStatusCodeNotDeletedAndPetTypeCode(petType.petTypeCode())) {
            throw new ResourceDuplicateException("Mã loại thú cưng đã tồn tại");
        }
    }

    public PetType update(Long petTypeId, PetType petType) {
        ValidationUtil.validateArgumentNotNull(petTypeId);
        ValidationUtil.validateNotNullPointerException(petType);

        return repository.findByStatusCodeNotDeletedAndPetTypeId(petTypeId)
                .map(x -> {
                    validateUpdate(x, petType);
                    mapper.update(x, petType);
                    PetTypeEntity savedPetType = repository.save(x);

                    return mapper.toDto(savedPetType);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    private void validateUpdate(PetTypeEntity existedPetType, PetType newPetType) {
        if(!Objects.equals(existedPetType.getPetTypeCode(), newPetType.petTypeCode())
            && repository.existsByStatusCodeNotDeletedAndPetTypeCode(newPetType.petTypeCode())) {
            throw new ResourceDuplicateException("Mã loại thú cưng đã tồn tại");
        }
    }

    public void delete(Long petTypeId) {
        ValidationUtil.validateArgumentNotNull(petTypeId);

        repository.findByStatusCodeNotDeletedAndPetTypeId(petTypeId)
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
