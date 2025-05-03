package com.paw.fund.app.modules.pet_management.controller.pet.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.pet.models.IPetModelMapper;
import com.paw.fund.app.modules.pet_management.controller.pet.models.PetRequest;
import com.paw.fund.app.modules.pet_management.controller.pet.models.PetResponse;
import com.paw.fund.app.modules.pet_management.controller.pet.v1.IPetV1API;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetFilter;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetSearchCriteria;
import com.paw.fund.app.modules.pet_management.service.pet.usecase.IPetUseCase;
import com.paw.fund.utils.request.PageRequestCustom;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetV1PubController implements IPetV1PubAPI {
    @NonNull
    IPetUseCase useCase;

    @NonNull
    IPetModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;


    @Override
    public PageResponse<PetResponse> getPetList(String search,
                                                Long shelterId,
                                                List<LocalDateTime> timeRange,
                                                List<LocalDate> receivedAtTimeRange,
                                                List<LocalDate> dateOfBirthTimeRange,
                                                List<String> petTypeCodes,
                                                List<String> petBreedCodes,
                                                List<String> petHobbyCodes,
                                                List<String> statusCodes,
                                                String sorter, Integer current, Integer pageSized) {
        PetSearchCriteria searchCriteria = PetSearchCriteria.of(
                search,
                shelterId,
                timeRange,
                receivedAtTimeRange,
                dateOfBirthTimeRange,
                petTypeCodes,
                petBreedCodes,
                petHobbyCodes,
                statusCodes);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSized, sorter);
        Page<PetResponse> responses = useCase.getPetList(PetFilter.of(searchCriteria, pageRequestCustom))
                .map(modelMapper::toResponse);

        return PageResponse.success(responses.getContent(), Meta.of(responses), HttpStatus.OK, API_VERSION);
    }
}
