package com.unipi.mns.mnscrm00.process.discounts.strategies;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.process.discounts.DiscountStrategy;

import java.util.Set;

public class IndustryDiscount implements DiscountStrategy {

    private final static Set<String> TARGET_INDUSTRIES = Set.of("IT", "Insurance");

    @Override
    public double getDiscountPercentage(Account account) {
        if (TARGET_INDUSTRIES.contains(account.getIndustry())) {
            return 0.04;
        }
        return 0.0;
    }
}
