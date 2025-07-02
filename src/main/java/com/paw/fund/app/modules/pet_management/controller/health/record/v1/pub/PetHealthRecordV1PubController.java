package com.paw.fund.app.modules.pet_management.controller.health.record.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.health.record.models.IPetHealthRecordModelMapper;
import com.paw.fund.app.modules.pet_management.controller.health.record.models.PetHealthRecordResponse;
import com.paw.fund.app.modules.pet_management.domain.health.record.usecase.PetHealthRecordFilter;
import com.paw.fund.app.modules.pet_management.domain.health.record.usecase.PetHealthRecordSearchCriteria;
import com.paw.fund.app.modules.pet_management.service.health.record.usecase.IPetHealthRecordUseCase;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.response.Meta;
import com.paw.fund.utils.response.PageResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetHealthRecordV1PubController implements IPetHealthRecordV1PubAPI {
    @NonNull
    IPetHealthRecordUseCase useCase;

    @NonNull
    IPetHealthRecordModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public PageResponse<PetHealthRecordResponse> getPetHealthRecordList(Long petId,
                                                                        String diagnosisSearch,
                                                                        String treatmentSearch,
                                                                        List<LocalDateTime> timeRange,
                                                                        List<LocalDateTime> checkupDateTimeRange,
                                                                        List<String> healthStatusCodes,
                                                                        String sort, Integer current, Integer pageSize) {
        PetHealthRecordSearchCriteria searchCriteria = PetHealthRecordSearchCriteria
                .of(petId, diagnosisSearch, treatmentSearch, timeRange, checkupDateTimeRange, healthStatusCodes);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize, sort);
        Page<PetHealthRecordResponse> responses = useCase.getPetHealthRecordList(PetHealthRecordFilter.of(searchCriteria, pageRequestCustom))
                .map(modelMapper::toResponse);

        return PageResponse.success(responses.getContent(), Meta.of(responses), HttpStatus.OK);
    }
}
