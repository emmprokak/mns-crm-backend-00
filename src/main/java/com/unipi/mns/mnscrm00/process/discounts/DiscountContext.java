package com.unipi.mns.mnscrm00.process.discounts;

import com.unipi.mns.mnscrm00.entities.data.Account;

public class DiscountContext {

    private DiscountStrategy strategy;

    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double executeStrategy(Account account) {
        return strategy.getDiscountPercentage(account);
    }
}
