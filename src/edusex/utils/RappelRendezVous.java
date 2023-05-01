/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusex.utils;

import java.time.LocalDate;
import edusex.utils.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 *
 * @author Chebbi_Mariem
 */
public class RappelRendezVous {

    public static void reminder(Date d) throws SchedulerException {
        // create a job detail for the job to be executed
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%-5p %d{ISO8601} [%t] %c: %m%n")));
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("myJob").build();

        // create a trigger to specify when the job will be executed
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger")
                .startAt(d)
                .build();

        // create a scheduler and schedule the job
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }

    private static Date getReminderDate() {
        // set the time for the reminder to one day before the appointment
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 0);
        cal.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE) +1);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }
}
