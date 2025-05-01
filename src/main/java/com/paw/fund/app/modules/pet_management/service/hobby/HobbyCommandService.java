package com.paw.fund.app.modules.pet_management.service.hobby;

import com.paw.fund.app.modules.pet_management.domain.hobby.Hobby;
import com.paw.fund.app.modules.pet_management.domain.hobby.IHobbyMapper;
import com.paw.fund.app.modules.pet_management.repository.database.hobby.HobbyEntity;
import com.paw.fund.app.modules.pet_management.repository.database.hobby.IHobbyRepository;
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

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HobbyCommandService {
    @NonNull
    IHobbyRepository repository;

    @NonNull
    IHobbyMapper mapper;

    public Hobby save(Hobby hobby) {
        ValidationUtil.validateNotNullPointerException(hobby);
        validateSave(hobby);

        HobbyEntity newHobby = mapper.toEntity(hobby);
        HobbyEntity savedHobby = repository.save(newHobby);

        return mapper.toDto(savedHobby);
    }

    private void validateSave(Hobby hobby) {
        if(repository.existsByStatusCodeNotDeletedAndHobbyCode(hobby.hobbyCode())) {
            throw new ResourceDuplicateException("Mã sở thích đã tồn tại");
        }
    }

    public List<Hobby> saveAll(List<Hobby> list) {
        ValidationUtil.validateArgumentListNotNull(list);
        validateSaveList(list);

        List<HobbyEntity> newHobby = list.stream().map(mapper::toEntity).toList();
        List<HobbyEntity> savedHobby = repository.saveAll(newHobby);

        return savedHobby.stream()
                .map(mapper::toDto)
                .toList();
    }

    private void validateSaveList(List<Hobby> list) {
        List<String> hobbyCodes = list.stream().map(Hobby::hobbyCode).toList();
        if(repository.existsByStatusCodeNotDeletedAndHobbyCodeIn(hobbyCodes)) {
            throw new ResourceDuplicateException("Mã sở thích đã tồn tại");
        }
    }

    public Hobby updateStatus(Long hobbyId, EPetInformationStatus status) {
        ValidationUtil.validateArgumentNotNull(hobbyId);
        ValidationUtil.validateArgumentNotNull(status);

        return repository.findByStatusCodeNotDeletedAndHobbyId(hobbyId)
                .map(x -> {
                    x.setStatusCode(status.getCode());
                    x.setStatusName(status.getName());
                    HobbyEntity updateHobby = repository.save(x);

                    return mapper.toDto(updateHobby);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    public void delete(Long hobbyId) {
        ValidationUtil.validateArgumentNotNull(hobbyId);

        repository.findByStatusCodeNotDeletedAndHobbyId(hobbyId)
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
