package com.paw.fund.app.modules.pet_management.controller.health.record.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.health.record.models.IPetHealthRecordModelMapper;
import com.paw.fund.app.modules.pet_management.controller.health.record.models.PetHealthRecordResponse;
import com.paw.fund.app.modules.pet_management.domain.health.record.PetHealthRecord;
import com.paw.fund.app.modules.pet_management.domain.health.record.usecase.PetHealthRecordId;
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
public class PetHealthRecordPathV1PubController implements IPetHealthRecordPathV1PubAPI {
    @NonNull
    IPetHealthRecordUseCase useCase;

    @NonNull
    IPetHealthRecordModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<PetHealthRecordResponse> getPetHealthRecordDetail(Long petHealthRecordId) {
        PetHealthRecord petHealthRecord = useCase.getPetHealthRecordDetail(PetHealthRecordId.of(petHealthRecordId));

        return ValueResponse.success(modelMapper.toResponse(petHealthRecord), HttpStatus.OK);
    }
}
