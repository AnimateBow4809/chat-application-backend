package bow.animate.cnchatapp.DAO;

import bow.animate.cnchatapp.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<ChatMessage,Long> {
}
