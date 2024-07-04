package bow.animate.cnchatapp.service.interfaces;

import bow.animate.cnchatapp.entity.Chat;
import bow.animate.cnchatapp.entity.ChatMessage;
import bow.animate.cnchatapp.entity.User;

import java.util.List;
import java.util.Map;

public interface ChatService {

    List<Chat> findByName(String name);

    List<ChatMessage> getChatMassages(Long id);

    Map<Object,Object> getChatMassagesAndUsers(Long id);

    List<Chat> findChatByUserName(String username, String query);

    Chat saveChat(Chat chat, List<User> users);
}
