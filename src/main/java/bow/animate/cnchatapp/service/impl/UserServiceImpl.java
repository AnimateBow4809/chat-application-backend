package bow.animate.cnchatapp.service.impl;

import bow.animate.cnchatapp.DAO.UserRepo;
import bow.animate.cnchatapp.entity.User;
import bow.animate.cnchatapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepo repo;

    @Autowired
    public void setRepo(UserRepo repo){
        this.repo=repo;
    }

    @Override
    public User findUserById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public User findUserByUserName(String userName) {
        return repo.findUserByUserName(userName);
    }

    @Override
    public List<User> findUserByLikeUserName(String likeUserName) {
        return repo.findUserByUserNameLike(likeUserName);
    }

    @Override
    public User login(String userName, String password) {
        return repo.login(userName,password);
    }

    @Override
    public User signUp(User user){
        return repo.save(user);
    }
}
