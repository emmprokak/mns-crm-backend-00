package com.unipi.mns.mnscrm00.dal;

import com.unipi.mns.mnscrm00.entities.data.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, String> {
}
