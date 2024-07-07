package com.unipi.mns.mnscrm00.dto.simples;

import com.unipi.mns.mnscrm00.dto.abstracts.LeadDTO;
import com.unipi.mns.mnscrm00.dto.minimals.LeadDTOMinimal;

public class LeadDTOSimple extends LeadDTOMinimal {

    public LeadDTOSimple(){
        super();
    }

    public LeadDTOSimple(String companyAddress, String companyIndustry, String companyName, String contactEmail, String contactMobile, String contactPerson, String contactPhone, String contactPrefix, String contactRole, String id, String status) {
        super(companyAddress, companyIndustry, companyName, contactEmail, contactMobile, contactPerson, contactPhone, contactPrefix, contactRole, id, status);
    }
}
