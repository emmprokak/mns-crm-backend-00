package com.unipi.mns.mnscrm00.dal;

import com.unipi.mns.mnscrm00.entities.data.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, String> {
}
