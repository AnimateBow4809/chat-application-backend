package bow.animate.cnchatapp.DAO;

import bow.animate.cnchatapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    @Query("select u from User u where u.userName=:userName")
    public User findUserByUserName(String userName);

    public List<User>findUserByUserNameLike(String userName);

    @Query("select u from User u where u.userName=:userName and u.password=:password")
    public User login(String userName ,String password);
}
