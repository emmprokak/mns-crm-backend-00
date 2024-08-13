package com.unipi.mns.mnscrm00.process.discounts;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.process.discounts.strategies.IndustryDiscount;
import com.unipi.mns.mnscrm00.process.discounts.strategies.LoyaltyDiscount;
import com.unipi.mns.mnscrm00.process.discounts.strategies.RevenueDiscount;
import com.unipi.mns.mnscrm00.utilities.strings.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class DiscountStrategyFactory {

    public DiscountStrategy getStrategy(Account account) {
        if (StringUtil.stringsAreEqual(account.getIndustry(), "IT") ||
                StringUtil.stringsAreEqual(account.getIndustry(), "Insurance")) {
            return new IndustryDiscount();
        }

        if (account.getRevenue() < 200_000) {
            return new RevenueDiscount();
        }

        return new LoyaltyDiscount();
    }
}
