package SpringJPA;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.security.test.context.support.WithMockUser;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;



@AutoConfigureMockMvc
@SpringBootTest
public class SchedulerTest {


    @Test
    @WithMockUser(username="User1")
    public void UserAPICallsReset(){
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


}
