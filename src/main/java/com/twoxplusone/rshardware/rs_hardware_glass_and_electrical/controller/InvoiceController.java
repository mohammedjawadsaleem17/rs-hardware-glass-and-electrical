package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.controller;

import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Entity.CustomerInvoices;
import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/create")
    public ResponseEntity<?> createInvoice(@RequestBody CustomerInvoices customerInvoices){
        CustomerInvoices invoice = invoiceService.createInvoice(customerInvoices);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAllInvoices(){
        List<CustomerInvoices> allInvoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(allInvoices,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchInvoiceById(@PathVariable String id){
        CustomerInvoices customerInvoices = invoiceService.fetchInvoiceById(id);
        return new ResponseEntity<>(customerInvoices,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        invoiceService.deleteInvoiceById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/ping")
    public String ping(){
        return "Pong";
    }


}
