package com.paw.fund.app.modules.pet_intake_registration_management.controller.websocket;

import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.IPetIntakeRegistrationModelMapper;
import com.paw.fund.app.modules.pet_intake_registration_management.controller.websocket.models.PetIntakeRegistrationPayload;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationFilter;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationInformerPhone;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationNotification;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationSearchCriteria;
import com.paw.fund.app.modules.pet_intake_registration_management.service.usecase.IPetIntakeRegistrationUseCase;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.websocket.MessageTemplateHandler;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetIntakeRegistrationWebSocketController {
    @NonNull
    IPetIntakeRegistrationUseCase useCase;

    @NonNull
    IPetIntakeRegistrationModelMapper modelMapper;

    @MessageMapping("/topic/pet-intake-registration/new")
    public void getPetIntakeRegistrationList(@Payload PetIntakeRegistrationPayload payload) {
        PetIntakeRegistrationSearchCriteria searchCriteria = PetIntakeRegistrationSearchCriteria
                .of(payload.search(), payload.timeRange(), payload.petTypeCodes(), payload.reasonTypeCodes(), List.of());
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(payload.current(), payload.pageSize(), payload.sorter());

        Page<PetIntakeRegistration> responses = useCase
                .getPetIntakeRegistrationList(PetIntakeRegistrationFilter.of(searchCriteria, pageRequestCustom));
        PetIntakeRegistrationNotification notifications = PetIntakeRegistrationNotification.of(responses.stream().toList());

        MessageTemplateHandler.sendToTopic("/topic/pet-intake-registration/new", notifications);
    }

    @MessageMapping("/topic/pet-intake-registration/{informerPhone}")
    public void getPetIntakeRegistrationDetail(@DestinationVariable String informerPhone) {
        List<PetIntakeRegistration> petIntakeRegistration = useCase
                .getPetIntakeRegistrationListByInformerPhone(PetIntakeRegistrationInformerPhone.of(informerPhone));
        MessageTemplateHandler.sendToTopic("/topic/pet-intake-registration/%s".formatted(informerPhone), petIntakeRegistration);
    }
}
