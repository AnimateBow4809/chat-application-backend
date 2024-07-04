package bow.animate.cnchatapp.service.interfaces;

import bow.animate.cnchatapp.entity.User;

import java.util.List;

public interface UserService {

    User findUserById(Long id);

    User findUserByUserName(String userName);

    List<User> findUserByLikeUserName(String likeUserName);

    User login(String userName,String password);

    User signUp(User user);
}
