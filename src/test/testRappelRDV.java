///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// 
//package test;
//
//import java.text.ParseException;
//import org.quartz.impl.StdSchedulerFactory;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import org.quartz.*;
//
///**
// *
// * @author Chebbi_Mariem
// */
//public class testRappelRDV {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws SchedulerException {
//        // Créer une nouvelle tâche de rappel
//        JobDetail job = JobBuilder.newJob(RappelJob.class)
//                .withIdentity("rappelJob", "group1")
//                .build();
//
//        // Définir le déclencheur pour exécuter la tâche tous les jours à 9h00
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("rappelTrigger", "group1")
//                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(23, 15))
//                .startAt(new Date())
//                .build();
//
//        // Planifier la tâche avec le déclencheur
//        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//        scheduler.start();
//        scheduler.scheduleJob(job, trigger);
//    }
//}
//
//// Classe pour exécuter la tâche de rappel
//class RappelJob implements Job {
//
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//        // Obtenir la date actuelle
//        Calendar today = Calendar.getInstance();
//        today.setTime(new Date());
//
//        // Ajouter un jour à la date actuelle
//        Calendar tomorrow = Calendar.getInstance();
//        tomorrow.setTime(today.getTime());
//        tomorrow.add(Calendar.DATE, 1);
//
//        // Récupérer les rendez-vous qui ont lieu demain
//        // (ici, nous simulons la récupération des rendez-vous avec un tableau)
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date[] rendezVous = null;
//        try {
//            rendezVous = new Date[]{
//                sdf.parse("18/04/2023 14:00:00"),
//                sdf.parse("18/04/2023 15:30:00"),
//                sdf.parse("18/04/2023 23:01:00")
//            };
//        } catch (ParseException ex) {
//            System.out.println("");
//        }
//        for (Date rdv : rendezVous) {
//            Calendar rdvDate = Calendar.getInstance();
//            rdvDate.setTime(rdv);
//            if (rdvDate.get(Calendar.YEAR) == tomorrow.get(Calendar.YEAR)
//                    && rdvDate.get(Calendar.MONTH) == tomorrow.get(Calendar.MONTH)
//                    && rdvDate.get(Calendar.DATE) == tomorrow.get(Calendar.DATE)) {
//                // Envoyer un rappel pour le rendez-vous
//                Calendar rappelDate = Calendar.getInstance();
//                rappelDate.setTime(rdv);
//                rappelDate.add(Calendar.DATE, -1);
//                System.out.println("Rappel pour le rendez-vous le " + sdf.format(rdv.getTime())
//                        + " : envoyer un rappel le " + sdf.format(rappelDate.getTime()));
//            }
//        }
//    }
//
//}
