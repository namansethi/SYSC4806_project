package SpringJPA;

import SpringJPA.Model.User;
import SpringJPA.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/json")
    public List<User> get(@RequestParam(value = "id") Long id) {
        return userRepository.findByUserId(id);
    }

    @PostMapping("/user/json")
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    //@DeleteMapping("/user?={id}")
    //public void delete(@PathVariable Long id) {
    //    userRepository.deleteById(id);
    //}

}