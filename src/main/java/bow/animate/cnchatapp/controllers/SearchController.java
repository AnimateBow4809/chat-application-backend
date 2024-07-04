package bow.animate.cnchatapp.controllers;

import bow.animate.cnchatapp.entity.Chat;
import bow.animate.cnchatapp.entity.User;
import bow.animate.cnchatapp.service.interfaces.ChatService;
import bow.animate.cnchatapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(originPatterns = "**")
public class SearchController {

    private UserService userService;

    private ChatService chatService;

    @Autowired
    private void setUserService(UserService userService){
        this.userService=userService;
    }

    @Autowired
    private void setChatService(ChatService chatService){
        this.chatService=chatService;
    }

    @PostMapping("/chats/and/user")
    public ResponseEntity<Map<Object,Object>>getSearchResults(@RequestBody Map<Object,Object> search) {
        try {
            String userName= (String) search.get("user");
            List<Chat> chats=chatService.findChatByUserName(userName, (String)search.get("query"));
            User user=userService.findUserByUserName((String)search.get("query"));
            Map<Object,Object> resMap=new LinkedHashMap<>();
            user.setPassword(null);
            user.setChats(null);
            user.setId(null);
            List<User>userList=new ArrayList<>();
            userList.add(user);
            resMap.put("chats",chats);
            resMap.put("user",userList);
            return ResponseEntity.ok(resMap);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PostMapping("/new/chat")
    public ResponseEntity<Chat> makeNewChat(@RequestBody List<User> users){
        try {
            Chat chat=new Chat();
            chat.setUsers(users);
            String name="";
            for (int i = 0; i < users.size(); i++) {
                name+=users.get(i).getUserName()+"-";
                users.get(i).setId(userService.findUserByUserName(users.get(i).getUserName()).getId());
            }
            Random rd=new Random();
            name+=String.valueOf(rd.nextDouble()).substring(2);
            chat.setName(name);
            return ResponseEntity.ok(chatService.saveChat(chat,users));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
