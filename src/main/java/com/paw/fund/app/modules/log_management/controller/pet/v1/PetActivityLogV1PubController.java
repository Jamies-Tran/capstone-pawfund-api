package com.paw.fund.app.modules.log_management.controller.pet.v1;

import com.paw.fund.app.modules.log_management.controller.pet.models.IPetActivityLogModelMapper;
import com.paw.fund.app.modules.log_management.controller.pet.models.PetActivityLogResponse;
import com.paw.fund.app.modules.log_management.domain.pet.usecase.PetActivityLogFilter;
import com.paw.fund.app.modules.log_management.domain.pet.usecase.PetActivityLogSearchCriteria;
import com.paw.fund.app.modules.log_management.service.pet.usecase.IPetActivityLogUseCase;
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
public class PetActivityLogV1PubController implements IPetActivityLogV1PubAPI {
    @NonNull
    IPetActivityLogUseCase useCase;

    @NonNull
    IPetActivityLogModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public PageResponse<PetActivityLogResponse> getPetActivityLogList(String accountSearch,
                                                                      String descriptionSearch,
                                                                      List<LocalDateTime> timeRange,
                                                                      List<String> actionCodes,
                                                                      String sorter, Integer current, Integer pageSize) {
        PetActivityLogSearchCriteria searchCriteria = PetActivityLogSearchCriteria
                .of(accountSearch, descriptionSearch, timeRange, actionCodes);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize, sorter);
        Page<PetActivityLogResponse> responses = useCase.getPetActivityLogList(PetActivityLogFilter.of(searchCriteria, pageRequestCustom))
                .map(modelMapper::toResponse);

        return PageResponse.success(responses.getContent(), Meta.of(responses), HttpStatus.OK, API_VERSION);
    }
}
