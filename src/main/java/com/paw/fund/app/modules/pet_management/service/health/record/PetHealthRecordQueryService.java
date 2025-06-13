package com.paw.fund.app.modules.pet_management.service.health.record;

import com.paw.fund.app.modules.pet_management.domain.health.record.IPetHealthRecordMapper;
import com.paw.fund.app.modules.pet_management.domain.health.record.PetHealthRecord;
import com.paw.fund.app.modules.pet_management.domain.health.record.PetHealthRecordAction;
import com.paw.fund.app.modules.pet_management.domain.health.record.usecase.PetHealthRecordSearchCriteria;
import com.paw.fund.app.modules.pet_management.repository.database.health.record.IPetHealthRecordRepository;
import com.paw.fund.app.modules.pet_management.repository.database.health.record.PetHealthRecordEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceDuplicateException;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.common.CurrentAccountLogin;
import com.paw.fund.utils.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetHealthRecordQueryService {
    @NonNull
    IPetHealthRecordRepository repository;

    @NonNull
    IPetHealthRecordMapper mapper;

    @NonNull
    RequestContext requestContext;

    public PetHealthRecord findById(Long petHealthRecordId) {
        return repository.findById(petHealthRecordId)
                .map(x -> {
                    PetHealthRecordAction action = prepareAction(x);

                    return mapper.toDto(x).withAction(action);
                })
                .orElseThrow(ResourceDuplicateException::new);
    }

    private PetHealthRecordAction prepareAction(PetHealthRecordEntity foundPetHealthRecord) {
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        Boolean allowUpdate = Objects.equals(foundPetHealthRecord.getCreatedById(), currentAccountLogin.accountId());
        Boolean allowDelete = Objects.equals(foundPetHealthRecord.getCreatedById(), currentAccountLogin.accountId());

        return PetHealthRecordAction.of(allowUpdate, allowDelete);
    }

    public Page<PetHealthRecord> findAll(PetHealthRecordSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(x -> {
                    PetHealthRecordAction action = prepareAction(x);

                    return mapper.toDto(x).withAction(action);
                });
    }
}
