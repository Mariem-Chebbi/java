package test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class AppointmentReminderScheduler {

    public static void main(String[] args) throws SchedulerException {
        // Use the scheduler factory to create a default scheduler
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%-5p %d{ISO8601} [%t] %c: %m%n")));
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();

        // Define the job and tie it to our AppointmentReminderJob class
        JobDetail job = JobBuilder.newJob(AppointmentReminderJob.class)
                .withIdentity("appointmentReminderJob", "appointmentReminders")
                .build();

        // Trigger the job to run at 8:00 AM the day before the appointment
        Date triggerDate = new Date(System.currentTimeMillis());
//        triggerDate.setHours(1);
//        triggerDate.setMinutes(21);
//        triggerDate.setSeconds(0);
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("appointmentReminderTrigger", "appointmentReminders")
                .startAt(triggerDate)
                .build();

        // Tell Quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);

        // Start the scheduler
        scheduler.start();
    }
}

class AppointmentReminderJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        // This method will be called by the Quartz scheduler when the job is triggered

        // Replace this with code to send a reminder message
        System.out.println("Sending reminder message...");
    }
}
