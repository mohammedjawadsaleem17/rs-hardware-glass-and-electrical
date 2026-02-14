package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
public class EmailScheduler {
    @Autowired
    private ScheduledEmailService scheduledEmailService;


    @Scheduled(cron = "0 0 9,21 * * *", zone = "Asia/Kolkata")
    public void sendDailyEmails() {
        LocalDate today = LocalDate.now();

        log.info("========================================");
        log.info("Daily email scheduler triggered at 9 AM");
        log.info("Date: {}, Day of month: {}", today, today.getDayOfMonth());
        log.info("========================================");

        // Check if we're in the email window
        if (!scheduledEmailService.isWithinEmailWindow()) {
            log.info("Outside email window (1-12th). Skipping email send.");
            return;
        }

        // Get recipients
        List<String> recipients = getRecipients();

        // Send emails
        scheduledEmailService.sendDailyEmailsToAll(recipients, "MONTHLY_REMINDER");
    }

    private List<String> getRecipients(){

        return List.of(
                "mohammedjawadsaleem17@gmail.com",
                "mohammedjawadsaleem8@gmail.com"
        );
    }

}
