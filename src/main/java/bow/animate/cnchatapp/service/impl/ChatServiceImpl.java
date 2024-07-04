package bow.animate.cnchatapp.service.impl;

import bow.animate.cnchatapp.DAO.ChatRepo;
import bow.animate.cnchatapp.entity.Chat;
import bow.animate.cnchatapp.entity.ChatMessage;
import bow.animate.cnchatapp.entity.User;
import bow.animate.cnchatapp.service.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    ChatRepo repo;

    @Autowired
    public void setRepo(ChatRepo repo){
        this.repo=repo;
    }

    @Override
    public List<Chat> findByName(String name) {
        return repo.findByNameLike(name);
    }

    @Override
    public List<ChatMessage> getChatMassages(Long id) {
        Optional<Chat> chat=repo.findById(id);
        return chat.map(Chat::getChatMessages).orElse(null);
    }

    @Override
    public Map<Object,Object> getChatMassagesAndUsers(Long id) {
        Optional<Chat> chat=repo.findById(id);
        Map<Object,Object> res=new HashMap<>();
        if (chat.isPresent()){
            res.put("messages",chat.get().getChatMessages());
            res.put("users",chat.get().getUsers());
            return res;
        }
        throw new RuntimeException("Couldent get chats and users");
    }

    @Override
    public List<Chat> findChatByUserName(String username, String query) {
        return repo.findChatByUsername(username,query);
    }

    @Override
    public Chat saveChat(Chat chat, List<User> users) {
        Chat c= repo.save(chat);
        Long id=c.getId();
        for (User user : users) {
            repo.addIntoJoinTable(id, user.getId());
        }
        return c;
    }

}
