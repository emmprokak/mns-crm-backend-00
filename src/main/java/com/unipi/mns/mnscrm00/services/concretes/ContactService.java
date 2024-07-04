package com.unipi.mns.mnscrm00.services.concretes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.ContactRepository;
import com.unipi.mns.mnscrm00.dto.abstracts.ContactDTO;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.mapping.ObjectMapper;
import com.unipi.mns.mnscrm00.services.abstracts.EntityService;
import com.unipi.mns.mnscrm00.utilities.error.ErrorMessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService implements EntityService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactDTO getContactByIdSimple(String id){
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if(!contactOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtility.getEntityNotFoundBySpecifier(Constants.Entity.getDescription(Constants.Entity.ACCOUNT), "id"));
        }

        return contactOptional.get().toDTOSimple();
    }

    public ContactDTO getContactByIdComplete(String id){
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if(!contactOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessageUtility.getEntityNotFoundBySpecifier(Constants.Entity.getDescription(Constants.Entity.ACCOUNT), "id"));
        }

        return contactOptional.get().toDTOComplete();
    }

    public ContactDTO insertContact(Contact contact){
        Contact contactToInsert = new Contact();

        contactToInsert = ObjectMapper.mapContactFields(contact, contactToInsert);

        return contactRepository.save(contactToInsert).toDTOSimple();
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactList = contactRepository.findAll();

        if(contactList.size() <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.CONSTANT_ERRORS.ENTITY_NOT_FOUND);
        }

        return contactList;
    }

    public Contact updateContact(String id, Contact contact){
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if(!contactOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        Contact contactToUpdate = contactOptional.get();
        contactToUpdate = ObjectMapper.mapContactFields(contact, contactToUpdate);

        return contactRepository.save(contactToUpdate);
    }

    public boolean deleteContactById(String id){
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if(!contactOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        contactRepository.delete(contactOptional.get());

        return true;
    }
}
