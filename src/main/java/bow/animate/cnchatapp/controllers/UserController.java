package bow.animate.cnchatapp.controllers;

import bow.animate.cnchatapp.entity.User;
import bow.animate.cnchatapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(originPatterns = "**")
public class UserController {
    private UserService userService;

    @Autowired
    private void setUserService(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        User authUser=userService.login(user.getUserName(),user.getPassword());
        if (authUser==null){
            return ResponseEntity.badRequest()
                    .header("status", "user not found")
                    .body(null);
        }else {
            authUser.setPassword(null);
            return ResponseEntity.ok(authUser);
        }
    }


    @PostMapping("/sign")
    public ResponseEntity<User> signUp(@RequestBody User user){
        user.setId(null);
        try {
            User newUser=userService.signUp(user);
            newUser.setPassword(null);
            return ResponseEntity.ok(newUser);
        }catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().body(null);
        }
    }


}
