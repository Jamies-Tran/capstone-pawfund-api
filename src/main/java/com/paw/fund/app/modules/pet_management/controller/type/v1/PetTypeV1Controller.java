package com.paw.fund.app.modules.pet_management.controller.type.v1;

import com.paw.fund.app.modules.pet_management.controller.type.models.IPetTypeModelMapper;
import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeListRequest;
import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeRequest;
import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeResponse;
import com.paw.fund.app.modules.pet_management.domain.type.PetType;
import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeFilter;
import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeList;
import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeSearchCriteria;
import com.paw.fund.app.modules.pet_management.service.type.usecase.IPetTypeUseCase;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.response.ListResponse;
import com.paw.fund.utils.response.Meta;
import com.paw.fund.utils.response.PageResponse;
import com.paw.fund.utils.response.ValueResponse;
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
public class PetTypeV1Controller implements IPetTypeV1API {
    @NonNull
    IPetTypeUseCase useCase;

    @NonNull
    IPetTypeModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<PetTypeResponse> createPetType(PetTypeRequest request) {
        PetType petType = modelMapper.toDto(request);
        PetType savePetType = useCase.createPetType(petType);

        return ValueResponse.success(modelMapper.toResponse(savePetType), HttpStatus.CREATED);
    }

    @Override
    public ListResponse<PetTypeResponse> createPetTypeList(PetTypeListRequest request) {
        List<PetType> list = request.list().stream()
                .map(modelMapper::toDto)
                .toList();
        List<PetTypeResponse> responseList = useCase.createPetTypeList(PetTypeList.of(list))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responseList, HttpStatus.OK);
    }

    @Override
    public PageResponse<PetTypeResponse> getPetTypeList(String search,
                                                        List<String> statusCodes,
                                                        Integer current, Integer pageSize) {
        PetTypeSearchCriteria searchCriteria = PetTypeSearchCriteria.of(search, statusCodes);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize);
        Page<PetTypeResponse> responses = useCase.getPetTypeList(PetTypeFilter.of(searchCriteria, pageRequestCustom))
                .map(modelMapper::toResponse);

        return PageResponse.success(responses.getContent(), Meta.of(responses), HttpStatus.OK);
    }
}
