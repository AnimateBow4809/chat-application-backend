package bow.animate.cnchatapp.service.interfaces;

import bow.animate.cnchatapp.entity.ChatMessage;

import java.util.List;
//TODO: make searching possible
public interface MessageService {

    public void saveMessage(ChatMessage chatMessage);
}
