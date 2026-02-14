package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Email;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${email.cc.address:mohammedjawadsaleem8@gmail.com}")
    private String ccEmail;

    public void sendEmail(String to,String subject,String body){
        //Email Sending logic
        try{
            log.info("Sending Email to: {}, Subject: {}", to, subject);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setCc(ccEmail);
            helper.setText(body, true);
            helper.setFrom("noreply@rshardwares.in", "RS Hardware");


            message.addHeader("X-Priority", "1"); // High priority
            message.addHeader("Importance", "High");
            message.addHeader("X-Mailer", "RS Hardware Notification System");
            message.addHeader("List-Unsubscribe", "<mailto:unsubscribe@rshardwares.in>");
            message.addHeader("Message-ID", "<" + System.currentTimeMillis() + "@rshardwares.in>");
            helper.setCc(new String[]{
                    "mohammedjawadsaleem8@gmail.com",
                    "rshardware2210@gmail.com"
            });
            javaMailSender.send(message);

            log.info("Email sent successfully to: {}", to);
        }catch (Exception e){
            log.error("Failed to Send email to : {} ",to,e);
        }


    }

}
