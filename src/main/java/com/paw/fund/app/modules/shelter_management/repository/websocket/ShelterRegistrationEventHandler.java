package com.paw.fund.app.modules.shelter_management.repository.websocket;

import com.paw.fund.app.modules.shelter_management.domain.registration.ShelterRegistration;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationEmail;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationFilter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationNotification;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationSearchCriteria;
import com.paw.fund.app.modules.shelter_management.service.usecase.IShelterRegistrationUseCase;
import com.paw.fund.enums.EShelterRegistrationStatus;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.websocket.MessageTemplateHandler;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShelterRegistrationEventHandler {
    @NonNull
    IShelterRegistrationUseCase useCase;

    @EventListener
    public void shelterRegistrationSubscribeEventHandler(SessionSubscribeEvent event) {
        String destination = (String) event.getMessage().getHeaders().get("simpDestination");
        if(Objects.equals(destination, "/topic/get-shelter-registration-topic")) {
            ShelterRegistrationNotification notification = useCase
                    .getRegistrationNotification(prepareFilter());
            MessageTemplateHandler.sendToTopic("/topic/get-shelter-registration-topic", notification);
        } else if(Objects.equals(destination, "/user/queue/get-shelter-registration-topic")) {
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ShelterRegistration shelterRegistration = useCase
                    .getShelterRegistrationDetail(ShelterRegistrationEmail.of(username));
            MessageTemplateHandler.sendToUser(username, "/user/queue/get-shelter-registration-topic", shelterRegistration);
        }
    }

    private ShelterRegistrationFilter prepareFilter() {
        ShelterRegistrationSearchCriteria searchCriteria = ShelterRegistrationSearchCriteria.builder()
                .statusCodes(List.of(EShelterRegistrationStatus.NEW.getCode()))
                .build();
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(0, 20, "requestAt_desc");

        return ShelterRegistrationFilter.of(searchCriteria, pageRequestCustom);
    }
}
