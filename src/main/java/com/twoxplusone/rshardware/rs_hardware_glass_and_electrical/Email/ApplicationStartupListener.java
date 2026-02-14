package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ApplicationStartupListener {

    private static final Logger log = LoggerFactory.getLogger(ApplicationStartupListener.class);
    @Autowired
    private ScheduledEmailService scheduledEmailService;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        log.info("========================================");
        log.info("Application started on: {}", LocalDate.now());
        log.info("========================================");

        try {
            checkAndSendPendingEmails();
        } catch (Exception e) {
            log.error("Error checking pending emails on startup", e);
        }
    }

    private void checkAndSendPendingEmails(){
        LocalDate today = LocalDate.now();
        int dayOfMonth = today.getDayOfMonth();
        log.info("Current day of month: {}", dayOfMonth);

        if(scheduledEmailService.isWithinEmailWindow()){
            log.info("Within email window (1-12th). Checking if today's emails need to be sent...");
            List<String> recipients = getRecipients();

            // Send emails to all recipients
            scheduledEmailService.sendDailyEmailsToAll(recipients, "MONTHLY_REMINDER");
        }else {
            log.info("Outside email window (1-12th). No emails will be sent today.");
        }
    }

    private List<String> getRecipients(){

        return List.of(
                "abdulfahad1436@gmail.com",
                "mohammedjawadsaleem8@gmail.com"
        );
    }

}
