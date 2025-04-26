package com.paw.fund.utils.websocket;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageTemplateHandler {

    static SimpMessagingTemplate messagingTemplate;

    @Autowired
    public void setMessageTemplate(SimpMessagingTemplate messagingTemplate) {
        MessageTemplateHandler.messagingTemplate = messagingTemplate;
    }

    public static void sendToTopic(String destination, Object payload) {
        messagingTemplate.convertAndSend(destination, payload);
    }

    public static void sendToUser(String user, String destination, Object payload) {
        messagingTemplate.convertAndSendToUser(user, destination, payload);
    }
}
