package SpringJPA;

import SpringJPA.Model.User;
import SpringJPA.Model.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest
public class SchedulerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Scheduler scheduler;

    @Test
    public void UserAPICallsCronTest(){
        CronTrigger trigger = new CronTrigger("0 0 0 * * ?");
        Instant now = Instant.now();

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss EEEE");
        Instant yesterday = now.minus(1, ChronoUnit.DAYS);
        System.out.println("Yesterday was: " + yesterday);
        Date nextExecutionTime = trigger.nextExecutionTime(
                new TriggerContext() {

                    @Override
                    public Date lastScheduledExecutionTime() {
                        return Date.from(yesterday);
                    }

                    @Override
                    public Date lastActualExecutionTime() {
                        return Date.from(yesterday);
                    }

                    @Override
                    public Date lastCompletionTime() {
                        return Date.from(yesterday);
                    }
                });

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        Date correctExecutionTime = cal.getTime();

        System.out.println("Next Execution date: " + df.format(nextExecutionTime));
        System.out.println("Correct execution date: " + df.format(correctExecutionTime));
        assertEquals(nextExecutionTime, correctExecutionTime);

    }

    @Test
    public void UserAPICallsResetTest(){


        User loadedUser = userRepository.findByUsername("User1");
        long originalAPICount = loadedUser.getApiCallCount();
        scheduler.wipeAPICallsMidnight();
        long newAPICount = userRepository.findByUsername("User1").getApiCallCount();

        System.out.println(originalAPICount);
        System.out.println(newAPICount);
        assertEquals(newAPICount, 0);


    }
}
