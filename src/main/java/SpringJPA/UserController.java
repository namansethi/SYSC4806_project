package SpringJPA;

import SpringJPA.Model.Customer;
import SpringJPA.Model.User;
import SpringJPA.Model.UserRepository;
import SpringJPA.Model.UserType;
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


    /*@PostMapping("user/admin/editRequests")
    public User editRequests(@RequestParam(value = "id") Long id, long apiCallLimit){
        User user = userRepository.findByUserId(id).get(0);
        user.setApiCallLimit(apiCallLimit);
        return userRepository.save(user);
    }*/

    /*@PostMapping("user/admin/changeStatus")
    public User changeStatus(@RequestParam(value = "id") Long id){
        User user = userRepository.findByUserId(id).get(0);
        user.setRole(user.getRole() == UserType.TRIAL ? UserType.PREMIUM : UserType.TRIAL);
        return userRepository.save(user);
    }*/

    @PostMapping("/user/json")
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }



    @DeleteMapping("/user/json")
    public void delete(@RequestParam(value = "id") Long id) {
        userRepository.deleteById(id);
    }

}