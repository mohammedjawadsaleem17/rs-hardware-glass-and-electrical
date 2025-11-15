package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Repository;

import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Entity.SmartSettleUsers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicRepository extends MongoRepository<SmartSettleUsers,Integer> {

}
