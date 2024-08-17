package com.unipi.mns.mnscrm00.process.discounts.strategies;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.process.discounts.DiscountStrategy;

public class DiscountCalculator {
    private DiscountStrategy discountStrategy;

    public DiscountCalculator(DiscountStrategy discountStrategy){
        this.discountStrategy = discountStrategy;
    }

   public double getDiscount(Account account){
        return discountStrategy.getDiscountPercentage(account);
   }
}
