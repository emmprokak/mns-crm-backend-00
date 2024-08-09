package com.unipi.mns.mnscrm00.controllers;

import com.unipi.mns.mnscrm00.dto.abstracts.ContactDTO;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.exceptions.DataValidationException;
import com.unipi.mns.mnscrm00.services.concretes.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    ContactService contactService;


    @GetMapping("/{id}")
    public ContactDTO getContact(@PathVariable String id){
        return contactService.getContactById(id, false);
    }

    @GetMapping("/{id}/complete")
    public ContactDTO getContactWithRels(@PathVariable String id){
        return contactService.getContactById(id, true);
    }

    @PostMapping("/new")
    public ContactDTO createContact(@RequestBody Contact contact) throws DataValidationException {
        return contactService.insertContact(contact);
    }

    @GetMapping("/all")
    public List<ContactDTO> getContacts(){
        return contactService.getAllContacts();
    }

    @PutMapping("/{id}")
    public ContactDTO updateContact(@PathVariable String id, @RequestBody Contact contact) throws DataValidationException {
        return contactService.updateContact(id, contact);
    }

    @DeleteMapping("/{id}")
    public boolean deleteContact(@PathVariable String id){
        return contactService.deleteContactById(id);
    }

}
