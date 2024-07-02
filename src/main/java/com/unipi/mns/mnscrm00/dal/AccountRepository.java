package com.unipi.mns.mnscrm00.dal;

import com.unipi.mns.mnscrm00.entities.data.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
