package com.paw.fund.app.modules.shelter_management.controller.api.v2;

import com.paw.fund.app.modules.shelter_management.controller.api.models.IShelterModelMapper;
import com.paw.fund.app.modules.shelter_management.controller.api.models.ShelterResponse;
import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterFilter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterSearchCriteria;
import com.paw.fund.app.modules.shelter_management.service.usecase.IShelterUseCase;
import com.paw.fund.enums.EShelterStatus;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterV2PubController implements IShelterV2PubAPI {
    @NonNull
    IShelterUseCase useCase;

    @NonNull
    IShelterModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public PageResponse<ShelterResponse> getShelterList(String search,
                                                        BigDecimal latitude,
                                                        BigDecimal longitude,
                                                        BigDecimal radius,
                                                        List<LocalDateTime> timeRange,
                                                        String sorter, Integer current, Integer pageSize) {
        ShelterSearchCriteria searchCriteria = ShelterSearchCriteria
                .of(search, latitude, longitude, radius, List.of(EShelterStatus.ENABLE.getCode()), timeRange);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize, sorter);
        Page<ShelterResponse> responses = useCase.getShelterList(ShelterFilter.of(searchCriteria, pageRequestCustom))
                .map(modelMapper::toResponse);

        return PageResponse.success(responses.getContent(), Meta.of(responses), HttpStatus.OK);
    }
}
