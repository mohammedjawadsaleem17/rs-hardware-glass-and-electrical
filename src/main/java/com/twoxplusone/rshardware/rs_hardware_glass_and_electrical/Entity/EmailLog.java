package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "email_logs")
public class EmailLog {
    @Id
    private String id;

    private String emailType;
    private String recipient;
    private LocalDate sentDate;
    private LocalDateTime sentDateTime;
    private String status;
    private String errorMessage;
}
