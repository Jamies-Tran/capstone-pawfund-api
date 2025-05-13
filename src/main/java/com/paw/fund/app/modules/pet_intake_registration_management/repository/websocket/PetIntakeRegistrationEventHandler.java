package com.paw.fund.app.modules.pet_intake_registration_management.repository.websocket;


import com.paw.fund.app.modules.pet_intake_registration_management.PetIntakeRegistrationModuleConstant;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.IPetIntakeRegistrationMapper;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationNotification;
import com.paw.fund.app.modules.pet_intake_registration_management.repository.database.IPetIntakeRegistrationRepository;
import com.paw.fund.utils.websocket.MessageTemplateHandler;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetIntakeRegistrationEventHandler {
    @NonNull
    IPetIntakeRegistrationRepository repository;

    @NonNull
    IPetIntakeRegistrationMapper mapper;

    String petIntakeRegistrationTopicDes = PetIntakeRegistrationModuleConstant.getAdminTopicDestination();

    @EventListener
    public void petIntakeRegistrationEventHandler(SessionSubscribeEvent event) {
        String destination = (String) event.getMessage().getHeaders().get("simpDestination");
        if(Objects.equals(destination, petIntakeRegistrationTopicDes)) {
            List<PetIntakeRegistration> petIntakeRegistrations = repository.findAllByStatusCodeNew()
                    .stream()
                    .map(mapper::toDto)
                    .toList();
            MessageTemplateHandler.sendToTopic(
                    petIntakeRegistrationTopicDes,
                    PetIntakeRegistrationNotification.of(petIntakeRegistrations));
        }
    }
}
