package com.paw.fund.app.modules.pet_management.controller.breed.v1;

import com.paw.fund.app.modules.pet_management.controller.breed.models.IPetBreedModelMapper;
import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedListRequest;
import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedRequest;
import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedResponse;
import com.paw.fund.app.modules.pet_management.domain.breed.PetBreed;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedFilter;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedList;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedSearchCriteria;
import com.paw.fund.app.modules.pet_management.service.breed.usecase.IPetBreedUseCase;
import com.paw.fund.enums.EPetInformationStatus;
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
public class PetBreedV1Controller implements IPetBreedV1API {
    @NonNull
    IPetBreedUseCase useCase;

    @NonNull
    IPetBreedModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<PetBreedResponse> createPetBreed(PetBreedRequest request) {
        PetBreed petBreed = modelMapper.toDto(request);
        PetBreed savedPetBreed = useCase.createPetBreed(petBreed);

        return ValueResponse.success(modelMapper.toResponse(savedPetBreed), HttpStatus.CREATED, API_VERSION);
    }

    @Override
    public ListResponse<PetBreedResponse> createPetBreedList(PetBreedListRequest request) {
        List<PetBreed> petBreeds = request.list().stream()
                .map(modelMapper::toDto)
                .toList();
        List<PetBreedResponse> savedResponses = useCase.createPetBreedList(PetBreedList.of(petBreeds))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(savedResponses, HttpStatus.CREATED, API_VERSION);
    }

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
