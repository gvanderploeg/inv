package com.gp.inv;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashTest {

    @Test
    public void test() {
        String password = "correctbatteryhorsestaple";
        String hash = new BCryptPasswordEncoder().encode(password);
        System.out.println("Hash for password " + password + ": " + hash);
    }
}
