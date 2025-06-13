package com.paw.fund.app.modules.pet_management.controller.hobby.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.hobby.models.HobbyResponse;
import com.paw.fund.app.modules.pet_management.controller.hobby.models.IHobbyModelMapper;
import com.paw.fund.app.modules.pet_management.domain.hobby.usecase.HobbyFilter;
import com.paw.fund.app.modules.pet_management.domain.hobby.usecase.HobbySearchCriteria;
import com.paw.fund.app.modules.pet_management.service.hobby.usecase.IHobbyUseCase;
import com.paw.fund.enums.EPetInformationStatus;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HobbyV1PubController implements IHobbyV1PubAPI {
    @NonNull
    IHobbyUseCase useCase;

    @NonNull
    IHobbyModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public PageResponse<HobbyResponse> getHobbyList(String search,
                                                    Long petTypeId,
                                                    String sorter, Integer current, Integer pageSize) {
        HobbySearchCriteria searchCriteria = HobbySearchCriteria.of(
                search,
                petTypeId,
                List.of(EPetInformationStatus.ACTIVE.getCode()));
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize, sorter);
        Page<HobbyResponse> responses = useCase.getHobbyList(HobbyFilter.of(searchCriteria, pageRequestCustom))
                .map(modelMapper::toResponse);

        return PageResponse.success(responses.getContent(), Meta.of(responses), HttpStatus.OK, API_VERSION);
    }
}
