package bow.animate.cnchatapp.controllers;

import bow.animate.cnchatapp.entity.Chat;
import bow.animate.cnchatapp.entity.ChatMessage;
import bow.animate.cnchatapp.service.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(originPatterns = "**")
@RequestMapping("/api/chat")
public class ChatController {
    private ChatService service;

    @Autowired
    public void setChatService(ChatService service){
        this.service=service;
    }

    @PostMapping("/getmessages")
    public ResponseEntity<List<ChatMessage>> getChatMessages(@RequestBody Chat chat){
        System.out.println(chat);
        try {
            List<ChatMessage>messages=service.getChatMassages(chat.getId());
            messages.sort(Comparator.comparing(ChatMessage::getSendDate));
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping("/getmessagesnew")
    public ResponseEntity<Map<Object, Object>> getChatMessagesnew(@RequestBody Chat chat){
        try {
            Map<Object, Object> res=service.getChatMassagesAndUsers(chat.getId());
            ((List<ChatMessage>)res.get("messages")).sort(Comparator.comparing(ChatMessage::getSendDate));
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().body(null);
        }
    }

}
