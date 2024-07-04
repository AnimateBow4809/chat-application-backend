package bow.animate.cnchatapp.controllers;

import bow.animate.cnchatapp.entity.Chat;
import bow.animate.cnchatapp.service.impl.ChatServiceImpl;
import bow.animate.cnchatapp.service.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    ChatService chatService;

//    @RequestMapping("")
//    public List<Chat> chats(@RequestBody String name){
//        return chatService.findByName(name);
//    }
}
