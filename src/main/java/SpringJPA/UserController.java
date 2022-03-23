package SpringJPA;

import SpringJPA.Model.Customer;
import SpringJPA.Model.User;
import SpringJPA.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public User getByUser(@RequestParam(value = "username") String user) {
        return userRepository.findByUsername(user);
    }


    @PostMapping("/user/json")
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/user/test")
    public String getUser(Principal principal) {
        String ret = "";
        User user = userRepository.findByUsername(principal.getName());
        ret += "Before: " + user.toString() + "<br>";
        user.incrementApICallCount();
        userRepository.save(user);
        ret += "After: " + user.toString();
        return ret;
    }

    @DeleteMapping("/user/json")
    public void delete(@RequestParam(value = "id") Long id) {
        userRepository.deleteById(id);
    }

}