package com.paw.fund.app.modules.log_management.service.pet;

import com.paw.fund.app.modules.log_management.domain.pet.PetActivityLog;
import com.paw.fund.app.modules.log_management.domain.pet.usecase.PetActivityLogFilter;
import com.paw.fund.app.modules.log_management.service.pet.usecase.IPetActivityLogUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetActivityLogUseCaseService implements IPetActivityLogUseCase {
    @NonNull
    PetActivityLogQueryService queryService;

    @Override
    public Page<PetActivityLog> getPetActivityLogList(PetActivityLogFilter filter) {
        return queryService.findAll(filter.searchCriteria(), filter.pageRequestCustom());
    }
}
