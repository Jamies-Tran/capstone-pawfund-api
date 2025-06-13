package com.paw.fund.app.modules.pet_management.service.health.record;

import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.app.modules.pet_management.domain.health.record.IPetHealthRecordMapper;
import com.paw.fund.app.modules.pet_management.domain.health.record.PetHealthRecord;
import com.paw.fund.app.modules.pet_management.repository.database.health.record.IPetHealthRecordRepository;
import com.paw.fund.app.modules.pet_management.repository.database.health.record.PetHealthRecordEntity;
import com.paw.fund.configuration.handler.exceptions.AuthenticationException;
import com.paw.fund.configuration.handler.exceptions.ResourceDuplicateException;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.common.CurrentAccountLogin;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true )
public class PetHealthRecordCommandService {
    @NonNull
    IPetHealthRecordRepository repository;

    @NonNull
    IPetHealthRecordMapper mapper;

    @NonNull
    IAuditableUseCase auditableUseCase;

    @NonNull
    RequestContext requestContext;

    public PetHealthRecord save(PetHealthRecord petHealthRecord) {
        ValidationUtil.validateNotNullPointerException(petHealthRecord);
        PetHealthRecordEntity newPetHealthRecord = mapper.toEntity(petHealthRecord);
        PetHealthRecordEntity savePetHealthRecord = repository.save(newPetHealthRecord);
        savePetHealthRecord.prepareSave(auditableUseCase.createAuditableForNew());

        return mapper.toDto(savePetHealthRecord);
    }

    public PetHealthRecord update(Long petHealthRecordId, PetHealthRecord petHealthRecord) {
        return repository.findById(petHealthRecordId)
                .map(x -> {
                    validateUpdate(x);
                    mapper.update(x, petHealthRecord);
                    x.prepareUpdate(auditableUseCase.createAuditableForUpdate());
                    PetHealthRecordEntity savePetHealthRecord = repository.save(x);

                    return mapper.toDto(savePetHealthRecord);
                })
                .orElseThrow(ResourceDuplicateException::new);
    }

    private void validateUpdate(PetHealthRecordEntity existedPetHealthRecord) {
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        if(!Objects.equals(existedPetHealthRecord.getCreatedById(), currentAccountLogin.accountId())) {
            throw new AuthenticationException("Bạn không có quyền cập nhật thông tin sổ khám bệnh thú cưng");
        }
    }
}
