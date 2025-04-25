package com.paw.fund.app.modules.shelter_management.controller.websocket;

import com.paw.fund.app.modules.shelter_management.domain.registration.ShelterRegistration;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationEmail;
import com.paw.fund.app.modules.shelter_management.service.usecase.IShelterRegistrationUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterRegistrationWebSocket {
    @NonNull
    IShelterRegistrationUseCase useCase;

    @NonNull
    SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/topic/shelter-registration-detail")
    public void getShelterRegistration(String email) {
        ShelterRegistration shelterRegistration = useCase
                .getShelterRegistrationDetail(ShelterRegistrationEmail.of(email));

        messagingTemplate.convertAndSendToUser(
                email, "/queue/shelter-registration-detail", shelterRegistration);
    }
}
