package com.paw.fund.app.modules.pet_management.controller.type.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.type.models.IPetTypeModelMapper;
import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeResponse;
import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeFilter;
import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeSearchCriteria;
import com.paw.fund.app.modules.pet_management.service.type.usecase.IPetTypeUseCase;
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
public class PetTypeV1PubController implements IPetTypeV1PubAPI {
    @NonNull
    IPetTypeUseCase useCase;

    @NonNull
    IPetTypeModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;


    @Override
    public PageResponse<PetTypeResponse> getPetTypeList(String search, Integer current, Integer pageSize) {
        PetTypeSearchCriteria searchCriteria = PetTypeSearchCriteria.of(search,
                List.of(EPetInformationStatus.ACTIVE.getCode()));
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize);
        Page<PetTypeResponse> responses = useCase.getPetTypeList(PetTypeFilter.of(searchCriteria, pageRequestCustom))
                .map(modelMapper::toResponse);

        return PageResponse.success(responses.getContent(), Meta.of(responses), HttpStatus.OK);
    }
}
