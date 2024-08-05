package com.unipi.mns.mnscrm00.dal;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository  extends JpaRepository<Case, String> {
}
