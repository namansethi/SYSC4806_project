package SpringJPA;

import SpringJPA.Model.User;
import SpringJPA.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class Scheduler {

    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void wipeAPICallsMidnight() {
        for (User user: userRepository.findAll()){
            user.setApiCallCount(0);
            userRepository.save(user);
        }
    }
}
