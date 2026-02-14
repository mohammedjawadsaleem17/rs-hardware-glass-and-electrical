package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Email;

import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Entity.EmailLog;
import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Repository.EmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ScheduledEmailService {
    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailRepository emailRepository;


    public boolean hasEmailBeenSentToday(String emailType,String recepient){
        LocalDate today = LocalDate.now();
        return emailRepository.existsByEmailTypeAndRecipientAndSentDateAndStatus(emailType,recepient,today,"SENT");
    }



    public void sendDailyEmail(String recipient,String emailType){
        LocalDate today = LocalDate.now();
        if(!isWithinEmailWindow()){
            log.info("Not within email window (1-12th) Today is day: {}",today.getDayOfMonth());
            return;
        }
        if(hasEmailBeenSentToday(emailType,recipient)){
            log.info("Email already sent today to: {} for type: {}", recipient, emailType);
            return;
        }
        try{
            // Prepare email content
            String subject = "Payment Reminder - RS Hardwares";
            String body = buildEmailBody(recipient, today);
            emailService.sendEmail(recipient,subject,body);

            logEmailAttempt(emailType,recipient,"SENT",null);
            log.info("Successfully sent email to: {} for date: {}", recipient, today);
        }catch (Exception e){
            log.error("Failed to send email to: {}", recipient, e);
            logEmailAttempt(emailType, recipient, "FAILED", e.getMessage());
        }

    }

    void sendDailyEmailsToAll(List<String> recipients, String emailType){
        if(!isWithinEmailWindow()){
            log.info("Not within email window. Skipping email send.");
            return;
        }
        log.info("Sending daily emails to {} recipients", recipients.size());

        for(String recipient:recipients){
            sendDailyEmail(recipient,emailType);
        }
    }

    public String buildEmailBody(String recipient, LocalDate date) {
        return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Payment Reminder</title>
        </head>
        <body style="margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%); min-height: 100vh; padding: 40px 20px;">
            
            <!-- Main Container -->
            <div style="max-width: 600px; margin: 0 auto; background: white; border-radius: 20px; overflow: hidden; box-shadow: 0 20px 60px rgba(0,0,0,0.3);">
                
                <!-- Header with Gradient -->
                <div style="background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%); padding: 40px 30px; text-align: center;">
                    <div style="background: white; width: 80px; height: 80px; border-radius: 50%%; margin: 0 auto 20px; display: flex; align-items: center; justify-content: center; box-shadow: 0 10px 30px rgba(0,0,0,0.2);">
                        <svg width="50" height="50" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M12 2L2 7L12 12L22 7L12 2Z" fill="#667eea"/>
                            <path d="M2 17L12 22L22 17" stroke="#667eea" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M2 12L12 17L22 12" stroke="#667eea" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                        </svg>
                    </div>
                    <h1 style="color: white; margin: 0; font-size: 32px; font-weight: 700; text-shadow: 0 2px 10px rgba(0,0,0,0.2);">
                        RS Hardware
                    </h1>
                    <p style="color: rgba(255,255,255,0.9); margin: 10px 0 0; font-size: 16px;">
                        Glass & Electricals
                    </p>
                </div>
                
                <!-- Content Section -->
                <div style="padding: 50px 40px;">
                    
                    <!-- Friendly Greeting -->
                    <h2 style="color: #2d3748; margin: 0 0 10px; font-size: 28px; font-weight: 600;">
                        Hey there! 👋
                    </h2>
                    
                    <p style="color: #4a5568; font-size: 16px; line-height: 1.6; margin: 0 0 30px;">
                        Please complete your monthly payment to keep your server up and running and avoid any interruption in service.
                    </p>
                    
                    <!-- Payment Amount Card - CENTERED AND BOLD -->
                    <div style="text-align: center; margin: 40px 0;">
                        <div style="background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%); border-radius: 20px; padding: 40px 30px; box-shadow: 0 15px 40px rgba(102,126,234,0.4); display: inline-block; min-width: 280px;">
                            <p style="color: rgba(255,255,255,0.9); font-size: 14px; margin: 0 0 10px; text-transform: uppercase; letter-spacing: 3px; font-weight: 600;">
                                Amount Due
                            </p>
                            <p style="color: white; font-size: 64px; font-weight: 800; margin: 0; line-height: 1; text-shadow: 0 4px 20px rgba(0,0,0,0.3);">
                                ₹582
                            </p>
                            <p style="color: rgba(255,255,255,0.85); font-size: 16px; margin: 15px 0 0; font-weight: 500;">
                                Monthly Subscription
                            </p>
                        </div>
                    </div>
                    
                    <!-- Pay Now Button -->
                    <div style="text-align: center; margin: 30px 0 40px;">
                        <a href="#" style="display: inline-block; background: linear-gradient(135deg, #f093fb 0%%, #f5576c 100%%); color: white; text-decoration: none; padding: 20px 60px; border-radius: 50px; font-weight: 700; font-size: 18px; box-shadow: 0 10px 30px rgba(245,87,108,0.4); text-transform: uppercase; letter-spacing: 1px; transition: all 0.3s ease;">
                            💳 Pay Now
                        </a>
                        <p style="color: #a0aec0; font-size: 13px; margin: 15px 0 0;">
                            Secure payment powered by trusted gateways
                        </p>
                    </div>
                    
                    <!-- Payment Info Card -->
                    <div style="background: linear-gradient(135deg, #4facfe 0%%, #00f2fe 100%%); border-radius: 15px; padding: 25px; margin: 30px 0; text-align: center; box-shadow: 0 10px 25px rgba(79,172,254,0.3);">
                        <p style="color: white; font-size: 14px; margin: 0 0 8px; text-transform: uppercase; letter-spacing: 2px; font-weight: 600;">
                            Payment Window
                        </p>
                        <p style="color: white; font-size: 24px; font-weight: 700; margin: 0;">
                            1st - 12th of Every Month
                        </p>
                        <p style="color: rgba(255,255,255,0.9); font-size: 14px; margin: 10px 0 0;">
                            Today is Day <strong style="font-size: 18px;">%d</strong>
                        </p>
                    </div>
                    
                    <!-- Divider -->
                    <div style="height: 2px; background: linear-gradient(to right, transparent, #e2e8f0, transparent); margin: 40px 0;"></div>
                    
                    <!-- Features Section -->
                    <div style="margin: 30px 0;">
                        <h3 style="color: #2d3748; font-size: 20px; margin: 0 0 20px; font-weight: 600;">
                            What You're Getting 🎯
                        </h3>
                        
                        <div style="display: grid; gap: 15px;">
                            <!-- Feature 1 -->
                            <div style="display: flex; align-items: start; gap: 15px;">
                               
                                <div>
                                    <p style="margin: 0; color: #2d3748; font-weight: 600; font-size: 16px;">Premium Glass Solutions</p>
                                    <p style="margin: 5px 0 0; color: #718096; font-size: 14px;">Top-quality materials and installation</p>
                                </div>
                            </div>
                            
                            <!-- Feature 2 -->
                            <div style="display: flex; align-items: start; gap: 15px;">
                              
                                <div>
                                    <p style="margin: 0; color: #2d3748; font-weight: 600; font-size: 16px;">Electrical Services</p>
                                    <p style="margin: 5px 0 0; color: #718096; font-size: 14px;">Expert electrical work and maintenance</p>
                                </div>
                            </div>
                            
                            <!-- Feature 3 -->
                            <div style="display: flex; align-items: start; gap: 15px;">
                              
                                <div>
                                    <p style="margin: 0; color: #2d3748; font-weight: 600; font-size: 16px;">24/7 Support</p>
                                    <p style="margin: 5px 0 0; color: #718096; font-size: 14px;">Always here when you need us</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Divider -->
                    <div style="height: 2px; background: linear-gradient(to right, transparent, #e2e8f0, transparent); margin: 40px 0;"></div>
                    
                    <!-- Already Paid Notice -->
                    <div style="background: #f7fafc; border-left: 4px solid #48bb78; padding: 20px; border-radius: 10px; margin: 30px 0;">
                        <p style="margin: 0; color: #2d3748; font-size: 15px; line-height: 1.6;">
                            <strong style="color: #48bb78;">✓ Already paid?</strong><br>
                            Awesome! Please ignore this reminder. Your payment might still be processing. Thanks for being a valued customer! 🙏
                        </p>
                    </div>
                    
                    <!-- Help Section -->
                    <div style="text-align: center; margin: 40px 0 20px;">
                        <p style="color: #718096; font-size: 14px; margin: 0 0 15px;">
                            Need help? We're here for you!
                        </p>
                        <div style="display: flex; justify-content: center; gap: 20px; flex-wrap: wrap;">
                            <a href="tel:+91 9380362812" style="color: #667eea; text-decoration: none; font-size: 14px; font-weight: 600;">
                                📞 &#160;Call Us
                            </a>
                            <a href="mailto:mohammedjawadsaleem8@gmail.com" style="color: #667eea; text-decoration: none; font-size: 14px; font-weight: 600;">
                                ✉️ &#160;Email Support
                            </a>
                            <a href="https://rshardwares.in" style="color: #667eea; text-decoration: none; font-size: 14px; font-weight: 600;">
                                🌐 &#160;Visit Website
                            </a>
                        </div>
                    </div>
                    
                </div>
                
                <!-- Footer -->
                <div style="background: #f7fafc; padding: 30px 40px; border-top: 1px solid #e2e8f0;">
                    <p style="color: #718096; font-size: 13px; line-height: 1.6; margin: 0 0 15px; text-align: center;">
                        This is an automated reminder. Payment window is from 1st to 12th of every month.
                    </p>
                    <p style="color: #a0aec0; font-size: 12px; margin: 0; text-align: center;">
                        © %d RS Hardware - Glass & Electricals<br>
                        <a href="https://rshardwares.in" style="color: #667eea; text-decoration: none;">rshardwares.in</a>
                    </p>
                </div>
                
            </div>
            
        </body>
        </html>
        """.formatted(date.getDayOfMonth(), date.getYear());
    }

    private void logEmailAttempt(String emailType,String recipient,String status,String errorMessage){
        EmailLog log = EmailLog.builder()
                .emailType(emailType)
                .recipient(recipient)
                .sentDate(LocalDate.now())
                .sentDateTime(LocalDateTime.now())
                .status(status)
                .errorMessage(errorMessage)
                .build();
        emailRepository.save(log);
    }

    public boolean isWithinEmailWindow(){
        int dayOfMonth = LocalDate.now().getDayOfMonth();
        return dayOfMonth>=1 && dayOfMonth<=12;
    }

}
