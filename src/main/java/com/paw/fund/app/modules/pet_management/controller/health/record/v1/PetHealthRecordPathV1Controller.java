package com.paw.fund.app.modules.pet_management.controller.health.record.v1;

import com.paw.fund.app.modules.pet_management.controller.health.record.models.IPetHealthRecordModelMapper;
import com.paw.fund.app.modules.pet_management.controller.health.record.models.PetHealthRecordResponse;
import com.paw.fund.app.modules.pet_management.controller.health.record.models.PetHealthRecordUpdateRequest;
import com.paw.fund.app.modules.pet_management.domain.health.record.PetHealthRecord;
import com.paw.fund.app.modules.pet_management.domain.health.record.usecase.PetHealthRecordUpdate;
import com.paw.fund.app.modules.pet_management.service.health.record.usecase.IPetHealthRecordUseCase;
import com.paw.fund.utils.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetHealthRecordPathV1Controller implements IPetHealthRecordPathV1API {
    @NonNull
    IPetHealthRecordUseCase useCase;

    @NonNull
    IPetHealthRecordModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<PetHealthRecordResponse> updatePetHealthRecord(Long petHealthRecordId,
                                                                        PetHealthRecordUpdateRequest request) {
        PetHealthRecord petHealthRecord = modelMapper.toDto(request);
        PetHealthRecord updatedPetHealthRecord = useCase.updatePetHealthRecord(PetHealthRecordUpdate.of(petHealthRecordId, petHealthRecord));

        return ValueResponse.success(modelMapper.toResponse(updatedPetHealthRecord), HttpStatus.OK, API_VERSION);
    }
}
