package com.paw.fund.app.modules.pet_management.controller.hobby.v1;

import com.paw.fund.app.modules.pet_management.controller.hobby.models.HobbyListRequest;
import com.paw.fund.app.modules.pet_management.controller.hobby.models.HobbyRequest;
import com.paw.fund.app.modules.pet_management.controller.hobby.models.HobbyResponse;
import com.paw.fund.app.modules.pet_management.controller.hobby.models.IHobbyModelMapper;
import com.paw.fund.app.modules.pet_management.domain.hobby.Hobby;
import com.paw.fund.app.modules.pet_management.domain.hobby.usecase.HobbyFilter;
import com.paw.fund.app.modules.pet_management.domain.hobby.usecase.HobbyList;
import com.paw.fund.app.modules.pet_management.domain.hobby.usecase.HobbySearchCriteria;
import com.paw.fund.app.modules.pet_management.service.hobby.usecase.IHobbyUseCase;
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
public class HobbyV1Controller implements IHobbyV1API {
    @NonNull
    IHobbyUseCase useCase;

    @NonNull
    IHobbyModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public PageResponse<HobbyResponse> getHobbyList(String search,
                                                    List<String> statusCodes,
                                                    String sorter, Integer current, Integer pageSize) {
        HobbySearchCriteria searchCriteria = HobbySearchCriteria.of(search, statusCodes);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize, sorter);
        Page<HobbyResponse> responses = useCase.getHobbyList(HobbyFilter.of(searchCriteria, pageRequestCustom))
                .map(modelMapper::toResponse);

        return PageResponse.success(responses.getContent(), Meta.of(responses), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<HobbyResponse> createHobby(Long petTypeId, HobbyRequest request) {
        Hobby hobby = modelMapper.toDto(request, petTypeId);
        Hobby savedHobby = useCase.createHobby(hobby);

        return ValueResponse.success(modelMapper.toResponse(savedHobby), HttpStatus.CREATED, API_VERSION);
    }

    @Override
    public ListResponse<HobbyResponse> createHobbyList(Long petTypeId, HobbyListRequest request) {
        List<Hobby> list = request.list().stream()
                .map(x -> modelMapper.toDto(x, petTypeId))
                .toList();
        List<HobbyResponse> responses = useCase.createHobbyList(HobbyList.of(list))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.CREATED, API_VERSION);
    }
}
