package com.twa.reservations;

import com.intuit.karate.junit5.Karate;
import com.twa.reservations.util.BaseTest;

class APITest extends BaseTest {

    @Karate.Test
    Karate runAllTests() {
        return Karate.run("actuator/health.feature", "flow/create_reservation.feature",
                "flow/payment_in_process.feature", "flow/payment_confirmed.feature").tags("~@ignore")
                .relativeTo(getClass());
    }
}