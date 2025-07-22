package com.bank;

public class Main {
    public static void main(String[] args) {
        try {
            Account acc = new Account("123456", "Isha Chiddarwar", 5000);
            acc.deposit(1500);
            acc.withdraw(2000);
            System.out.println(acc.getDetails());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class Account {
    private final String accountNumber;
    private final String accountHolder;
    private double balance;

    public Account(String accountNumber, String accountHolder, double initialBalance) {
        if (initialBalance < 0)
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Deposit amount must be positive.");
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0)
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        if (amount > balance)
            throw new InsufficientFundsException("Insufficient balance.");
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getDetails() {
        return String.format("Account[%s] Holder: %s | Balance: %.2f",
                             accountNumber, accountHolder, balance);
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

