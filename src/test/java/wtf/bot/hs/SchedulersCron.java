package wtf.bot.hs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.CronExpression;
import org.quartz.JobDetail;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//import org.springframework.scheduling.support.CronExpression;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
//@EnableScheduling
public class SchedulersCron {

//    @Test
    public void uTest() {
        Calendar calendar = Calendar.getInstance();
        try {
            CronExpression cronExpression = new CronExpression(calendar.toString());
            System.out.println(cronExpression.getCronExpression());
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        CronExpression cronExpression = CronExpression.parse("10 * * * * *");
//        cronExpression.next(LocalDateTime.now());
//        System.out.println(cronExpression);
    }

//    @Test
    public void uTest2() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        CronTrigger cronTrigger = new CronTrigger("0/5 * * * * *");

        PeriodicTrigger periodicTrigger = new PeriodicTrigger(2000, TimeUnit.MICROSECONDS);
        periodicTrigger.setFixedRate(true);
        periodicTrigger.setInitialDelay(1000);

        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.initialize();

        taskScheduler.schedule(new Runnable() {
                                   @Override
                                   public void run() {
                                       System.out.println(Thread.currentThread().getName() + " | threadCount: " + atomicInteger.incrementAndGet());
                                   }
                               }
                , cronTrigger);

        while (true) {
            System.out.println("Thread sleeping for 60 sec | ActiveThreads: " + Thread.activeCount());
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    @Test
//    public void uTest3() {
//
////        SimpleThreadPoolTaskExecutor simpleThreadPoolTaskExecutor;
//        JobDetail jobDetail;
//        CronSequenceGenerator cronSequenceGenerator;
////        DelegatingJob delegatingJob;
//
//
//
//    }
}
