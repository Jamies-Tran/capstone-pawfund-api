package com.paw.fund.app.modules.shelter_assignment_management.controller.websocket;

import com.paw.fund.app.modules.shelter_assignment_management.controller.websocket.models.ShelterAssignmentSearchCriteriaPayload;
import com.paw.fund.app.modules.shelter_assignment_management.domain.ShelterAssignment;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentFilter;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentSearchCriteria;
import com.paw.fund.app.modules.shelter_assignment_management.service.usecase.IShelterAssignmentUseCase;
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

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterAssignmentWebSocketController {
    @NonNull
    IShelterAssignmentUseCase useCase;

    @MessageMapping("/topic/shelter-assignment/{shelterId}")
    public void getShelterAssignmentList(@DestinationVariable Long shelterId,
                                         @Payload ShelterAssignmentSearchCriteriaPayload payload) {
        ShelterAssignmentSearchCriteria searchCriteria = ShelterAssignmentSearchCriteria
                .of(shelterId, payload.petIntakeRegistrationId(), payload.timeRange(), payload.statusCodes());
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(payload.current(), payload.pageSize(), payload.sorter());
        Page<ShelterAssignment> shelterAssignments = useCase
                .getShelterAssignmentList(ShelterAssignmentFilter.of(searchCriteria, pageRequestCustom));

        MessageTemplateHandler.sendToTopic("/topic/shelter-assignment/%s".formatted(shelterId), shelterAssignments.getContent());
    }
}