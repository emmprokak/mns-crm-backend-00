package com.unipi.mns.mnscrm00.dto.minimals;

import com.unipi.mns.mnscrm00.dto.abstracts.LeadDTO;

public class LeadDTOMinimal extends LeadDTO {
    public LeadDTOMinimal(){
        super();
    }

    public LeadDTOMinimal(String companyAddress, String companyIndustry, String companyName, String contactEmail, String contactMobile, String contactPerson, String contactPhone, String contactPrefix, String contactRole, String id, String status) {
        super(companyAddress, companyIndustry, companyName, contactEmail, contactMobile, contactPerson, contactPhone, contactPrefix, contactRole, id, status);
    }
}
