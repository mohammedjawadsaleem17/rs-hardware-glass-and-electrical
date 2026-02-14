package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Repository;

import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Entity.EmailLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmailRepository extends MongoRepository<EmailLog,String> {
    boolean existsByEmailTypeAndRecipientAndSentDateAndStatus(String emailType, String recipient, LocalDate sentDate,String status);

//    List<EmailLog> findRecipientAndSentDate(String recipient,LocalDate sentDate);
}
