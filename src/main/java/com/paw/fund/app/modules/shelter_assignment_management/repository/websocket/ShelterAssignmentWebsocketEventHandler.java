package com.paw.fund.app.modules.shelter_assignment_management.repository.websocket;

import com.paw.fund.app.modules.shelter_assignment_management.domain.IShelterAssignmentMapper;
import com.paw.fund.app.modules.shelter_assignment_management.domain.ShelterAssignment;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentSearchCriteria;
import com.paw.fund.app.modules.shelter_assignment_management.repository.database.IShelterAssignmentRepository;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.websocket.MessageTemplateHandler;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterAssignmentWebsocketEventHandler {
    @NonNull
    IShelterAssignmentRepository repository;

    @NonNull
    IShelterAssignmentMapper mapper;

    @EventListener
    public void shelterAssignmentEventHandler(SessionSubscribeEvent event) {
        String destination = (String) event.getMessage().getHeaders().get("simpDestination");
        if(Objects.nonNull(destination) && destination.startsWith("/topic/shelter-assignment/")) {
            try {
                Long shelterId = Long.parseLong(destination.substring("/topic/shelter-assignment/".length()));
                ShelterAssignmentSearchCriteria searchCriteria = ShelterAssignmentSearchCriteria.ofDefault(shelterId);
                PageRequestCustom pageRequestCustom = PageRequestCustom.of(0, 25, "updatedAt");
                Page<ShelterAssignment> shelterAssignments = repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                        .map(mapper::toDto);
                MessageTemplateHandler.sendToTopic("/topic/shelter-assignment/%s".formatted(shelterId), shelterAssignments.getContent());
            } catch (Exception e) {}
        }
    }
}
