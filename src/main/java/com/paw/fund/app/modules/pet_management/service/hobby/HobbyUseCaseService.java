package com.paw.fund.app.modules.pet_management.service.hobby;

import com.paw.fund.app.modules.pet_management.domain.hobby.Hobby;
import com.paw.fund.app.modules.pet_management.domain.hobby.usecase.HobbyFilter;
import com.paw.fund.app.modules.pet_management.domain.hobby.usecase.HobbyId;
import com.paw.fund.app.modules.pet_management.domain.hobby.usecase.HobbyList;
import com.paw.fund.app.modules.pet_management.service.hobby.usecase.IHobbyUseCase;
import com.paw.fund.enums.EPetInformationStatus;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HobbyUseCaseService implements IHobbyUseCase {
    @NonNull
    HobbyCommandService commandService;

    @NonNull
    HobbyQueryService queryService;

    @Override
    @Transactional
    public Hobby createHobby(Hobby hobby) {
        return commandService.save(hobby);
    }

    @Override
    @Transactional
    public List<Hobby> createHobbyList(HobbyList hobbyList) {
        return commandService.saveAll(hobbyList.list());
    }

    @Override
    public Hobby getHobbyDetail(HobbyId hobbyId) {
        return queryService.findById(hobbyId.value());
    }

    @Override
    public Page<Hobby> getHobbyList(HobbyFilter filter) {
        return queryService.findAll(filter.searchCriteria(), filter.pageRequestCustom());
    }

    @Override
    @Transactional
    public Hobby activeHobby(HobbyId hobbyId) {
        return commandService.updateStatus(hobbyId.value(), EPetInformationStatus.ACTIVE);
    }

    @Override
    @Transactional
    public Hobby blockHobby(HobbyId hobbyId) {
        return commandService.updateStatus(hobbyId.value(), EPetInformationStatus.BLOCK);
    }

    @Override
    @Transactional
    public void deleteHobby(HobbyId hobbyId) {
        commandService.delete(hobbyId.value());
    }
}
