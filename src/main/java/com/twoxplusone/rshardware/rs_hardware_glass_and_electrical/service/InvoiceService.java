package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.service;

import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Entity.CustomerInvoices;
import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Repository.InvoiceRepositoy;
import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.dtos.ApiResponse;
import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.liveconfig.Payment;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.togglz.core.manager.FeatureManager;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepositoy invoiceRepositoy;

    @Autowired
    private FeatureManager featureManager;

    public ResponseEntity<?> createInvoice(CustomerInvoices customerInvoices){
        if(featureManager.isActive(Payment.PAYMENT_RECEIVED)){
            CustomerInvoices saveInvoice = invoiceRepositoy.save(customerInvoices);
            return ResponseEntity.ok(saveInvoice);
        }else {
            ApiResponse serverNotRenewed = ApiResponse.builder()
                    .message("Server Not Renewed, Contact support.")
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(serverNotRenewed);
        }
    }

    public List<CustomerInvoices> getAllInvoices() {
        if(featureManager.isActive(Payment.PAYMENT_RECEIVED)){
        List<CustomerInvoices> allInvoices = invoiceRepositoy.findAll();

        return allInvoices.stream()
                .sorted(Comparator.comparing((CustomerInvoices invoice) -> {
                    String d = invoice.getDated();

                    // handle null, empty, or blank dates
                    if (d == null || d.trim().isEmpty()) {
                        return LocalDate.MIN; // send to bottom
                    }

                    try {
                        return LocalDate.parse(d); // parse valid date
                    } catch (Exception e) {
                        return LocalDate.MIN; // fallback for bad format
                    }
                }).reversed())
                .toList();
        }else {
            return List.of();
        }
    }

    public CustomerInvoices fetchInvoiceById(String id){
        if(featureManager.isActive(Payment.PAYMENT_RECEIVED)){
            CustomerInvoices customerInvoices = invoiceRepositoy.findById(id).orElse(null);
            return customerInvoices;
        }else{
            return null;
        }

    }

    public void deleteInvoiceById(String id){
        if(featureManager.isActive(Payment.PAYMENT_RECEIVED)){
            invoiceRepositoy.deleteById(id);
        }

    }

}
