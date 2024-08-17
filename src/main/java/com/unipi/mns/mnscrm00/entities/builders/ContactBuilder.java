package com.unipi.mns.mnscrm00.entities.builders;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Contact;

import java.util.Date;

public class ContactBuilder implements EntityBuilder<Contact>{
    private Contact contact;

    @Override
    public Contact build() {
        return contact;
    }

    public ContactBuilder(){
        this.contact = new Contact();
    }

    public ContactBuilder(Contact acc){
        this.contact = acc;
    }

    public ContactBuilder setFirstName(String firstName) {
        contact.setFirstName(firstName);
        return this;
    }

    public ContactBuilder setLastName(String lastName) {
        contact.setLastName(lastName);
        return this;
    }

    public ContactBuilder setEmail(String email) {
        contact.setEmail(email);
        return this;
    }

    public ContactBuilder setPhone(String phone) {
        contact.setPhone(phone);
        return this;
    }

    public ContactBuilder setMobile(String mobile) {
        contact.setMobile(mobile);
        return this;
    }

    public ContactBuilder setPrefix(String prefix) {
        contact.setPrefix(prefix);
        return this;
    }

    public ContactBuilder setActive(boolean isActive) {
        contact.setActive(isActive);
        return this;
    }

    public ContactBuilder setBirthdate(Date birthdate) {
        if(birthdate != null){
            contact.setBirthdate(birthdate);
        }
        return this;
    }

    public ContactBuilder setAccount(Account account) {
        this.contact.setAccount(account);
        return this;
    }

    public ContactBuilder setDepartment(String department) {
        contact.setDepartment(department);
        return this;
    }

    public ContactBuilder setRole(String role) {
        contact.setRole(role);
        return this;
    }

    public ContactBuilder setAccountId(String accountId) {
        contact.setAccountId(accountId);
        return this;
    }
}
