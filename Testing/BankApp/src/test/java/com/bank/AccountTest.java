package com.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    void depositAndWithdrawTest() throws InsufficientFundsException {
        Account acc = new Account("001", "Tester", 1000);
        acc.deposit(500);
        assertEquals(1500, acc.getBalance());

        acc.withdraw(300);
        assertEquals(1200, acc.getBalance());
    }

    @Test
    void testNegativeDepositThrowsException() {
        Account acc = new Account("002", "Tester", 1000);
        assertThrows(IllegalArgumentException.class, () -> acc.deposit(-200));
    }

    @Test
    void testOverdrawThrowsException() {
        Account acc = new Account("003", "Tester", 1000);
        assertThrows(InsufficientFundsException.class, () -> acc.withdraw(1500));
    }
}

