package com.unipi.mns.mnscrm00.process.discounts.strategies;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.process.discounts.DiscountStrategy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LoyaltyDiscount implements DiscountStrategy {

    @Override
    public double getDiscountPercentage(Account account) {
        return account.getClientRating() >= 9 ? 0.03 : 0.0;
    }
}
