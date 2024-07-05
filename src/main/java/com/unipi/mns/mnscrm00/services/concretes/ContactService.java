package com.unipi.mns.mnscrm00.services.concretes;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dal.ContactRepository;
import com.unipi.mns.mnscrm00.dto.abstracts.ContactDTO;
import com.unipi.mns.mnscrm00.entities.data.Contact;
import com.unipi.mns.mnscrm00.mapping.ObjectMapper;
import com.unipi.mns.mnscrm00.services.abstracts.EntityService;
import com.unipi.mns.mnscrm00.utilities.ListConverter;
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
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.CONTACT,
                            Constants.Specifier.ID
                    )
            );
        }

        return contactOptional.get().toDTOSimple();
    }

    public ContactDTO getContactByIdComplete(String id){
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if(!contactOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.CONTACT,
                            Constants.Specifier.ID
                    )
            );
        }

        return contactOptional.get().toDTOComplete();
    }

    public ContactDTO insertContact(Contact contact){
        Contact contactToInsert = new Contact();

        contactToInsert = ObjectMapper.mapContactFields(contact, contactToInsert);

        return contactRepository.save(contactToInsert).toDTOSimple();
    }

    public List<ContactDTO> getAllContacts(){
        List<Contact> contactList = contactRepository.findAll();

        if(contactList.size() <= 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.CONTACT,
                            Constants.Specifier.ID
                    )
            );
        }

        return ListConverter.convertContactsToDTOList(contactList, Constants.DTO.CONVERT_TO_DTO_MINIMAL);
    }

    public ContactDTO updateContact(String id, Contact contact){
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if(!contactOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.CONTACT,
                            Constants.Specifier.ID
                    )
            );
        }

        Contact contactToUpdate = contactOptional.get();
        contactToUpdate = ObjectMapper.mapContactFields(contact, contactToUpdate);

        return contactRepository.save(contactToUpdate).toDTOSimple();
    }

    public boolean deleteContactById(String id){
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if(!contactOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageUtility.getEntityNotFoundBySpecifier(
                            Constants.Entity.CONTACT,
                            Constants.Specifier.ID
                    )
            );
        }

        contactRepository.delete(contactOptional.get());

        return true;
    }
}
