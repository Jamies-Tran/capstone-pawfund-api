package com.paw.fund.app.modules.pet_management.controller.breed.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.breed.models.IPetBreedModelMapper;
import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedResponse;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedFilter;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedSearchCriteria;
import com.paw.fund.app.modules.pet_management.service.breed.usecase.IPetBreedUseCase;
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
public class PetBreedV1PubController implements IPetBreedV1PubAPI {
    @NonNull
    IPetBreedUseCase useCase;

    @NonNull
    IPetBreedModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public PageResponse<PetBreedResponse> getPetBreedList(String search,
                                                          List<String> statusCodes,
                                                          Integer current, Integer pageSize) {
        PetBreedSearchCriteria searchCriteria = PetBreedSearchCriteria.of(search, statusCodes);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize);
        Page<PetBreedResponse> responses = useCase.getPetBreedList(PetBreedFilter.of(searchCriteria, pageRequestCustom))
                .map(modelMapper::toResponse);

        return PageResponse.success(responses.getContent(), Meta.of(responses), HttpStatus.OK, API_VERSION);
    }
}
