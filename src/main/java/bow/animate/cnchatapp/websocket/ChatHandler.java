package bow.animate.cnchatapp.websocket;

import bow.animate.cnchatapp.entity.ChatMessage;
import bow.animate.cnchatapp.service.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatHandler {


    @Autowired
    private SimpMessagingTemplate messageSender;

    @Autowired
    private MessageService service;


    @MessageMapping("/chat/send/{receiver}")
    @SendTo("/topic/public/{receiver}")
    public ChatMessage sendMessage(@DestinationVariable String receiver, @Payload ChatMessage chatMessage) {
//        System.out.println(chatMessage);
//        messageSender.convertAndSend("/topic/public/" + chatMessage.getSender().getUserName(), chatMessage);
//        System.out.println("wow");
        service.saveMessage(chatMessage);
        return chatMessage;
    }


}
