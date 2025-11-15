package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.service;

import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Entity.CustomerInvoices;
import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Repository.InvoiceRepositoy;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepositoy invoiceRepositoy;


    public CustomerInvoices createInvoice(CustomerInvoices customerInvoices){
        CustomerInvoices saveInvoice = invoiceRepositoy.save(customerInvoices);
        return saveInvoice;

    }

    public List<CustomerInvoices> getAllInvoices() {
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
    }

    public CustomerInvoices fetchInvoiceById(String id){
        CustomerInvoices customerInvoices = invoiceRepositoy.findById(id).orElse(null);
        return customerInvoices;
    }

    public void deleteInvoiceById(String id){
        invoiceRepositoy.deleteById(id);
    }

}
