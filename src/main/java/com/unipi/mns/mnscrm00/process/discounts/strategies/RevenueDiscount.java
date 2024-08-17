package com.unipi.mns.mnscrm00.process.discounts.strategies;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.process.discounts.DiscountStrategy;

public class RevenueDiscount implements DiscountStrategy {

    @Override
    public double getDiscountPercentage(Account account) {
        if (account.getRevenue() < 1_000_000) {
            return 0.05;
        }

        return 0.0;
    }
}
