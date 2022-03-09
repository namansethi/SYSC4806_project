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

    @GetMapping("/user/all")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/user/username")
    public List<User> getByUser(@RequestParam(value = "username") String user) {
        return userRepository.findByUsername(user);
    }

    @PostMapping("/user/json")
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }



    @DeleteMapping("/user/json")
    public void delete(@RequestParam(value = "id") Long id) {
        userRepository.deleteById(id);
    }

}