package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.liveconfig;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;

public enum Payment implements Feature   {

    @EnabledByDefault
    @Label("Payment received")
    PAYMENT_RECEIVED,

    @Label("payment not received")
    PAYMENT_NOT_RECEIVED,

    @Label("bypas")
    BYPASS,


}
