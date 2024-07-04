package bow.animate.cnchatapp.DAO;

import bow.animate.cnchatapp.entity.Chat;
import bow.animate.cnchatapp.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChatRepo extends JpaRepository<Chat,Long> {


    @Query("select c from Chat c where c.name like concat('%',:name,'%') ")
    public List<Chat> findByNameLike(String name);


    @Query(value = " select c.id,c.name,c.start_date from  USER_CHAT uc,chat c , APP_USER u where u.user_name=:userName and " +
            " uc.CHAT_ID=c.id and uc.USER_ID=u.id and c.name like concat(concat('%',:query),'%') ",nativeQuery = true )
    public List<Chat> findChatByUsername(String userName,String query);

    @Modifying
    @Transactional
    @Query(value = "insert into USER_CHAT(chat_id,user_id) values (:chatId,:userId)",nativeQuery = true)
    public void addIntoJoinTable(Long chatId,Long userId);
}
