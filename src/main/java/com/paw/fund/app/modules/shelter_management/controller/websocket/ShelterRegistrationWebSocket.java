package com.paw.fund.app.modules.shelter_management.controller.websocket;

import com.paw.fund.app.modules.shelter_management.controller.websocket.models.RequestAtTimeRangeMessage;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationFilter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationNotification;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationSearchCriteria;
import com.paw.fund.app.modules.shelter_management.service.usecase.IShelterRegistrationUseCase;
import com.paw.fund.enums.EShelterRegistrationStatus;
import com.paw.fund.utils.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterRegistrationWebSocket {
    @NonNull
    IShelterRegistrationUseCase useCase;

    @MessageMapping("/shelter-registration-message")
    @SendTo("/topic/get-shelter-registration-topic")
    public ShelterRegistrationNotification getShelterRegistrationNotifications(RequestAtTimeRangeMessage message) {
        return useCase.getRegistrationNotification(prepareFilter(message.requestAtTimeRange()));
    }

    private ShelterRegistrationFilter prepareFilter(List<LocalDateTime> requestAtTimeRange) {
        ShelterRegistrationSearchCriteria searchCriteria = ShelterRegistrationSearchCriteria.builder()
                .requestAtTimeRange(requestAtTimeRange)
                .statusCodes(List.of(EShelterRegistrationStatus.NEW.getCode()))
                .build();
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(0, 20, "requestAt_desc");

        return ShelterRegistrationFilter.of(searchCriteria, pageRequestCustom);
    }
}
