package com.paw.fund.app.modules.pet_management.controller.type.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.type.models.IPetTypeModelMapper;
import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeResponse;
import com.paw.fund.app.modules.pet_management.domain.type.PetType;
import com.paw.fund.app.modules.pet_management.domain.type.usecase.PetTypeId;
import com.paw.fund.app.modules.pet_management.service.type.usecase.IPetTypeUseCase;
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
public class PetTypePathV1PubController implements IPetTypePathV1PubAPI {
    @NonNull
    IPetTypeUseCase useCase;

    @NonNull
    IPetTypeModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<PetTypeResponse> getPetTypeDetail(Long petTypeId) {
        PetType petType = useCase.getPetTypeDetail(PetTypeId.of(petTypeId));

        return ValueResponse.success(modelMapper.toResponse(petType), HttpStatus.OK, API_VERSION);
    }
}
