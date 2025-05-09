package com.paw.fund.app.modules.log_management.service.pet;

import com.paw.fund.app.modules.log_management.domain.pet.IPetActivityLogMapper;
import com.paw.fund.app.modules.log_management.domain.pet.PetActivityLog;
import com.paw.fund.app.modules.log_management.domain.pet.usecase.PetActivityLogSearchCriteria;
import com.paw.fund.app.modules.log_management.repository.databse.pet.IPetActivityLogRepository;
import com.paw.fund.utils.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetActivityLogQueryService {
    @NonNull
    IPetActivityLogRepository repository;

    @NonNull
    IPetActivityLogMapper mapper;

    public Page<PetActivityLog> findAll(PetActivityLogSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(mapper::toDto);
    }
}
