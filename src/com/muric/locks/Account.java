package com.muric.locks;

public class Account {
    private Double balance = .0;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void add(Double amount){
        balance += amount;
    }

    public void subtract(Double amount) throws OutOfFundsException {
        if(balance - amount >= 0.0){
            balance -= amount;
        } else {
            throw new OutOfFundsException("Cannot withdraw " + amount + ", balance is " + balance);
        }
    }

    @Override
    public String toString(){
        return "Account balance is " + balance;
    }
}
