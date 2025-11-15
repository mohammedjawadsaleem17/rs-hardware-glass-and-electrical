package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Repository;

import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Entity.CustomerInvoices;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InvoiceRepositoy extends MongoRepository<CustomerInvoices,String> {
    Optional<CustomerInvoices> findById(String id);
}
