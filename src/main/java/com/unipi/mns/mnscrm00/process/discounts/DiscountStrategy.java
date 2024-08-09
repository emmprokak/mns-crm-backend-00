package com.unipi.mns.mnscrm00.process.discounts;

import com.unipi.mns.mnscrm00.entities.data.Account;

public interface DiscountStrategy {
    double getDiscountPercentage(Account account);
}
