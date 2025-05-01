package com.paw.fund.app.modules.pet_management.controller.hobby.v1;

import com.paw.fund.app.modules.pet_management.controller.hobby.models.HobbyResponse;
import com.paw.fund.app.modules.pet_management.controller.hobby.models.IHobbyModelMapper;
import com.paw.fund.app.modules.pet_management.domain.hobby.Hobby;
import com.paw.fund.app.modules.pet_management.domain.hobby.usecase.HobbyId;
import com.paw.fund.app.modules.pet_management.service.hobby.usecase.IHobbyUseCase;
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
public class HobbyPathV1Controller implements IHobbyPathV1API {
    @NonNull
    IHobbyUseCase useCase;

    @NonNull
    IHobbyModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<HobbyResponse> getHobbyDetail(Long hobbyId) {
        Hobby hobby = useCase.getHobbyDetail(HobbyId.of(hobbyId));

        return ValueResponse.success(modelMapper.toResponse(hobby), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<HobbyResponse> activeHobby(Long hobbyId) {
        Hobby hobby = useCase.activeHobby(HobbyId.of(hobbyId));

        return ValueResponse.success(modelMapper.toResponse(hobby), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<HobbyResponse> blockHobby(Long hobbyId) {
        Hobby hobby = useCase.blockHobby(HobbyId.of(hobbyId));

        return ValueResponse.success(modelMapper.toResponse(hobby), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<HobbyResponse> deleteHobby(Long hobbyId) {
        useCase.deleteHobby(HobbyId.of(hobbyId));

        return ValueResponse.success(null, HttpStatus.NO_CONTENT, API_VERSION);
    }
}
