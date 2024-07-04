package bow.animate.cnchatapp.service.impl;

import bow.animate.cnchatapp.DAO.MessageRepo;
import bow.animate.cnchatapp.entity.ChatMessage;
import bow.animate.cnchatapp.service.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    MessageRepo repo;

    @Autowired
    public void setRepo(MessageRepo repo){
        this.repo=repo;
    }

    @Override
    public void saveMessage(ChatMessage chatMessage) {
        repo.save(chatMessage);
    }
}
